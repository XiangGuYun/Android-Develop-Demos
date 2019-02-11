package com.androidui.huitu.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils
import com.scwang.smartrefresh.layout.util.DensityUtil

class DrawOvalView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint:Paint = Paint()
    var left = 0f
    var top = 0f
    var right = context.srnWidth/2f
    var bottom = DensityUtil.dp2px(100f).toFloat()

    init {
        setBackgroundColor(Color.WHITE)
        paint.color = Color.RED
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas?.drawOval(left, top, right, bottom, paint)
        }else{
            canvas?.drawOval(RectF(left, top, right, bottom), paint)
        }
    }

}