package com.androidui.draw.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils
import com.kotlinlib.other.DensityUtils


class Path1View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint:Paint = Paint()
    val paint1:Paint = Paint()
    val rect = Rect()

    init {
        setBackgroundColor(Color.WHITE)
        paint.color = Color.RED
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        paint1.textSize = 20f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //初始化Path
        val path = Path()
        //将坐标系原点从（0,0）移动到（100,100）
        path.moveTo(10f, 10f)
        //画从（100,100）到（400,400）之间的直线
        path.lineTo(40f, 40f)
//        path.rMoveTo(0f, 10f)
        path.lineTo(40f, 80f)
        canvas?.drawPath(path, paint)
        showCoordinate(canvas)
    }

    private fun showCoordinate(canvas: Canvas?) {
        val text1 = "(0, 0)"
        paint1.getTextBounds(text1, 0, text1.length, rect)
        canvas?.drawText("(0, 0) 图1-moveTo", 0f, rect.height().toFloat(), paint1)
    }

    fun Number.sp():Int{
        return DensityUtils.px2sp(context, this.toFloat())
    }

}