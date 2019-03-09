package com.android.draw.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils
import com.kotlinlib.other.DensityUtils


class Path5View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint:Paint = Paint()
    val paint1:Paint = Paint()
    val rect = Rect()

    init {
        setBackgroundColor(Color.WHITE)
        paint.color = Color.RED
        paint.strokeWidth = 1f
        paint.style = Paint.Style.STROKE
        paint.textSize = 20f
        paint1.textSize = 20f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val path = Path()
        //以（600,600）为圆心，300为半径绘制圆
        //Path.Direction.CW顺时针绘制圆 Path.Direction.CCW逆时针绘制圆
        path.addCircle(width/2f, height/2f, width/3f, Path.Direction.CW)
        //沿path绘制文字
        canvas?.drawTextOnPath("A、B、C、D、E、F、G、H、I、J、K、L、M、N、O、P、Q、R、S、T、U、V、W、X、Y、Z。", path, 0f, 0f, paint)
        canvas?.drawPath(path, paint)
        showCoordinate(canvas)
    }

    private fun showCoordinate(canvas: Canvas?) {
        val text1 = "(0, 0)"
        paint1.getTextBounds(text1, 0, text1.length, rect)
        canvas?.drawText("(0, 0) 图3-CW", 0f, rect.height().toFloat(), paint1)
    }

    fun Number.sp():Int{
        return DensityUtils.px2sp(context, this.toFloat())
    }

}