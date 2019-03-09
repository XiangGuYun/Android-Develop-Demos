package com.android.draw.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils


class CanvasLayerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint = Paint()
    val textPaint = Paint()

    init {
        paint.color = Color.CYAN
        textPaint.color = Color.BLACK
        textPaint.textSize = 30f
    }

    private var mWidth: Int = 0

    private var mHeight: Int = 0

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.CYAN)
        mWidth = width
        mHeight = height
        paint.color = Color.BLACK
        val text = "初始时图层数量是${canvas?.saveCount}"
        val th = textPaint.getTextRect(text).height().toFloat()
        canvas?.drawText(text,(width - paint.getTextRect(text).width())/2f,th,textPaint)

        canvas?.drawLine(0f, height/2f, width.toFloat(), height/2f, paint)
        canvas?.drawLine(width/2f, 0f, width/2f, height.toFloat(), paint)

        canvas?.save()
        val text1 = "调用save()后图层数量是${canvas?.saveCount}"
        canvas?.drawText(text1,(width - paint.getTextRect(text1).width())/2f,th*2,textPaint)

        paint.color = Color.RED
        canvas?.translate(width/2f,height/2f)
        canvas?.drawRect(0f,0f,100f,50f, paint)

        canvas?.save()
        val text2 = "再次调用save()后图层数量是${canvas?.saveCount}"
        canvas?.drawText(text2,0f,th,textPaint)
        canvas?.rotate(60f)
        paint.color = Color.YELLOW
        canvas?.drawRect(0f,0f,100f,50f, paint)

        canvas?.restoreToCount(1)
        val text3 = "最后调用restoreToCount(1)后图层数量是${canvas?.saveCount}"
        canvas?.drawText(text3,(width - paint.getTextRect(text3).width())/2f,height-th,textPaint)

        paint.color = Color.BLUE
        canvas?.drawRect(0f,0f,100f,50f, paint)

        canvas?.translate(width/2f,height/2f)

    }



}