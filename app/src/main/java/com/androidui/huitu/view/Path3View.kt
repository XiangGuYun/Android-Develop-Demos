package com.androidui.huitu.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils
import com.kotlinlib.other.DensityUtils
import com.scwang.smartrefresh.layout.util.DensityUtil


class Path3View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint:Paint = Paint()
    val paint1:Paint = Paint()
    val rect = Rect()

    init {
        setBackgroundColor(Color.WHITE)
        paint.color = Color.RED
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        paint1.textSize = 20.sp().toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //初始化Path
        val path = Path()
        //将坐标系原点从(0,0)移动到(10,10)
        path.moveTo(10f, 10f)
        //画从(10,10)到(40,40)之间的直线
        path.lineTo(40f, 40f)
        //新加的setLastPoint
        path.setLastPoint(10f, 80f)
        path.lineTo(40f, 80f)
        canvas?.drawPath(path, paint)
        showCoordinate(canvas)
    }

    private fun showCoordinate(canvas: Canvas?) {
        val text1 = "(0, 0)"
        paint1.getTextBounds(text1, 0, text1.length, rect)
        canvas?.drawText("(0, 0) 图3-setLastPoint", 0f, rect.height().toFloat(), paint1)
    }

    fun Number.sp():Int{
        return DensityUtils.px2sp(context, this.toFloat())
    }

}