package com.android.customview.view_study

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.other.BaseInterface
import kotlin.random.Random

class PostInvalidateView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), BaseInterface{

    val paint = Paint()

    init {
        paint.apply {
            textSize = 60f
            color = Color.WHITE
        }
    }

    override fun draw(canvas: Canvas?) {
        canvas?.drawColor("#${getRandColorCode()}".color())
        val text = Random.nextInt().toString()
        val rect = Rect()
        paint.getTextBounds(text, 0, text.length, rect)
        canvas?.drawText(text, (width-rect.width())/2f, (height+rect.height())/2f, paint)
        super.draw(canvas)
        Thread{
            Thread.sleep(1000)
            postInvalidate()
        }.start()
    }

}