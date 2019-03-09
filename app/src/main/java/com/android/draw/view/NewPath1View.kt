package com.android.draw.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils


class NewPath1View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint:Paint = Paint()
    val path = Path()

    init {
        setBackgroundColor(Color.CYAN)
        paint.color = Color.RED
        paint.strokeWidth = 15f
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //初始化Path
        canvas?.translate(width/2f, height/2f)

        path.moveTo(0f,-120f)
        path.lineTo(100f,60f)
        path.lineTo(-100f,60f)
        path.close()

        canvas?.drawPath(path, paint)

        path.moveTo(-100f,-60f)
        path.lineTo(100f,-60f)
        path.lineTo(0f,120f)
        path.close()

        canvas?.drawPath(path, paint)

    }

}