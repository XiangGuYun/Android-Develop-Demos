package com.androidui.draw.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils
import com.scwang.smartrefresh.layout.util.DensityUtil

class DrawLineView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint:Paint = Paint()
    var startX = 0f
    var startY = 0f
    var stopX = context.srnWidth/2f
    var stopY = DensityUtil.dp2px(100f).toFloat()

    init {
        setBackgroundColor(Color.WHITE)
        paint.color = Color.RED
        paint.isAntiAlias = true
        paint.strokeWidth = 2f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawLine(startX, startY, stopX, stopY, paint)
    }

}