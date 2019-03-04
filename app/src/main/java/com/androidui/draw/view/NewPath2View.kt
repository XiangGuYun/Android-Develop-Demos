package com.androidui.draw.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils
import com.kotlinlib.other.StringUtils


class NewPath2View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils, StringUtils{

    val paint:Paint = Paint()
    val paint1:Paint = Paint()
    val path = Path()

    init {
        setBackgroundColor(Color.CYAN)
        paint.strokeWidth = 15f
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawLine(width/2f,0f,width/2f,height.toFloat(),paint1)
        canvas?.drawLine(0f,height/2f,width.toFloat(),height/2f,paint1)

        //初始化Path
        canvas?.translate(width/2f, height/2f)

        path.moveTo(0f,-120f)
        path.lineTo(100f,60f)
        path.lineTo(-100f,60f)
        path.setLastPoint(0f,0f)
        path.close()

        canvas?.drawPath(path, paint)

    }

}