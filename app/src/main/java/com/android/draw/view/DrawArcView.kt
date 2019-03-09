package com.android.draw.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils
import com.scwang.smartrefresh.layout.util.DensityUtil
import android.graphics.DashPathEffect



class DrawArcView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint:Paint = Paint()
    val paintLine = Paint()
    var left = 0f
    var top = 0f
    var right = context.srnWidth/2f
    var bottom = DensityUtil.dp2px(100f).toFloat()
    var startAngle = 0f
    var sweepAngle = 90f
    var useCenter = true

    init {
        setBackgroundColor(Color.WHITE)
        paint.color = Color.RED
        paint.isAntiAlias = true
        paintLine.pathEffect = DashPathEffect(floatArrayOf(4f, 8f), 0f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawLine(0f, DensityUtil.dp2px(100f).toFloat(),
                context.srnWidth.toFloat(), DensityUtil.dp2px(100f).toFloat(), paintLine)

        canvas?.drawLine(context.srnWidth/2.toFloat(), 0f,
                context.srnWidth/2.toFloat(), DensityUtil.dp2px(200f).toFloat(), paintLine)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas?.drawArc(left,top,right,bottom, startAngle, sweepAngle, useCenter, paint)
        }else{
            canvas?.drawArc(RectF(left,top,right,bottom), startAngle, sweepAngle, useCenter, paint)
        }
    }

}