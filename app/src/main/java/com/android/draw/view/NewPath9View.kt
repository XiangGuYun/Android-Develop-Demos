package com.android.draw.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils
import com.kotlinlib.other.StringUtils


class NewPath9View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils, StringUtils{

    val paint:Paint = Paint()
    val paint1:Paint = Paint()
    val path = Path()

    init {
        setBackgroundColor(Color.CYAN)
        paint.strokeWidth = 15f
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawLine(width/2f,0f,width/2f,height.toFloat(),paint1)
        canvas?.drawLine(0f,height/2f,width.toFloat(),height/2f,paint1)

        path.moveTo(0f,0f)

        //初始化Path
        canvas?.translate(width/2f, height/2f)

        path.addRect(-width/2f+50,-height/2f+50,width/2f-50,height/2f-50, Path.Direction.CW)
        canvas?.drawPath(path, paint)
    }

}