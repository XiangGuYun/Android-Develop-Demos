package com.androidui.draw.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.androidui.R
import com.kotlinlib.activity.ContextUtils


class Bitmap1View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint = Paint()

    init {
        paint.textSize = 35f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.CYAN)
        canvas?.drawBitmap(BitmapFactory.decodeResource(context.resources, R.mipmap.girl1), Matrix() , Paint())
        listOf("canvas?.drawBitmap(BitmapFactory.decodeResource","(context.resources, R.mipmap.girl1), Matrix() , Paint())")
                .forEachIndexed {i,it->
                    canvas?.drawText(it, (width-paint.getTextRect(it).width())/2f, height/2f+i*paint.getTextRect(it).height(), paint)
                }
    }



}