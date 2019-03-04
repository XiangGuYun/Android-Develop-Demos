package com.androidui.draw.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils
import com.kotlinlib.other.DensityUtils
import android.graphics.RectF
import android.os.Build


class Path6View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint:Paint = Paint()
    val paint1:Paint = Paint()
    val rect = Rect()

    init {
        setBackgroundColor(Color.WHITE)
        paint.color = Color.RED
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
        paint.textSize = 20f
        paint1.textSize = 20f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val path = Path()
        //以（400,200）为圆心，半径为100绘制圆
        path.addCircle(400f, 200f, 100f, Path.Direction.CW)

        //绘制椭圆
        val rectF = RectF(100f, 350f, 500f, 600f)
        //第一种方法绘制椭圆
        path.addOval(rectF, Path.Direction.CW)
        //第二种方法绘制椭圆
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            path.addOval(600f, 350f, 1000f, 600f, Path.Direction.CW)
        }

        //绘制矩形
        val rect = RectF(100f, 650f, 500f, 900f)
        //第一种方法绘制矩形
        path.addRect(rect, Path.Direction.CW)
        //第二种方法绘制矩形
        path.addRect(600f, 650f, 1000f, 900f, Path.Direction.CCW)

        //绘制圆角矩形
        val roundRect = RectF(100f, 950f, 300f, 1100f)
        //第一种方法绘制圆角矩形
        path.addRoundRect(roundRect, 20f, 20f, Path.Direction.CW)
        //第二种方法绘制圆角矩形
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            path.addRoundRect(350f, 950f, 550f, 1100f, 10f, 50f, Path.Direction.CCW)
        }
        //第三种方法绘制圆角矩形
        //float[] radii中有8个值，依次为左上角，右上角，右下角，左下角的rx,ry
        val roundRectT = RectF(600f, 950f, 800f, 1100f)
        path.addRoundRect(roundRectT, floatArrayOf(50f, 50f, 50f, 50f, 50f, 50f, 0f, 0f), Path.Direction.CCW)
        //第四种方法绘制圆角矩形
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            path.addRoundRect(850f, 950f, 1050f, 1100f, floatArrayOf(0f, 0f, 0f, 0f, 50f, 50f, 50f, 50f), Path.Direction.CCW)
        }
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