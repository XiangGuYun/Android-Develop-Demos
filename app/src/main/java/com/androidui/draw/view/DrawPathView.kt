package com.androidui.draw.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils
import com.kotlinlib.other.DensityUtils
import com.scwang.smartrefresh.layout.util.DensityUtil


class DrawPathView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint:Paint = Paint()
    val paint1:Paint = Paint()
    var nextX = 0f
    var nextY = 0f
    var dx = 0f
    var dy = 0f
    val path = Path()
    val rect = Rect()
    var isClose = false
    var moveToX = 0f
    var moveToY = 0f

    init {
        setBackgroundColor(Color.WHITE)
        paint.color = Color.RED
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        paint1.textSize = 20f

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        path.moveTo(moveToX, moveToY)
        path.lineTo(nextX, nextY)
        path.rLineTo(dx, dy)
        canvas?.drawPath(path, paint)
        showCoordinate(canvas)
        if(isClose){
            path.close()
        }
    }

    private fun showCoordinate(canvas: Canvas?) {
        val text1 = "(0, 0)"
        paint1.getTextBounds(text1, 0, text1.length, rect)
        canvas?.drawText("(0, 0)", 0f, rect.height().toFloat(), paint1)
        val text2 = "(0, ${width.toFloat()})"
        paint1.getTextBounds(text2, 0, text2.length, rect)
        canvas?.drawText(text2, width.toFloat()-rect.width(), rect.height().toFloat(), paint1)
        val text3 = "(0, ${DensityUtil.dp2px(200f).toFloat()})"
        paint1.getTextBounds(text3, 0, text3.length, rect)
        canvas?.drawText(text3, 0f, DensityUtil.dp2px(200f).toFloat()-5, paint1)
    }

    fun Number.sp():Int{
        return DensityUtils.px2sp(context, this.toFloat())
    }

}