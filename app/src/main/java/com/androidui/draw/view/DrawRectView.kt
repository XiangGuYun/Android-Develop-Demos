package com.androidui.draw.view

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

class DrawRectView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint:Paint = Paint()
    var left = 0f
    var top = 0f
    var right = context.srnWidth/2f
    var bottom = DensityUtil.dp2px(100f).toFloat()
    var isRround = false //是否是圆角
    var rx = 10f
    var ry = 10f

    init {
        setBackgroundColor(Color.WHITE)
        paint.color = Color.RED
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(!isRround){
            canvas?.drawRect(left, top, right, bottom, paint)
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas?.drawRoundRect(left, top, right, bottom, rx, ry, paint)
            }else{
                canvas?.drawRoundRect(RectF(left, top, right, bottom), rx, ry, paint)
            }
        }
    }

}