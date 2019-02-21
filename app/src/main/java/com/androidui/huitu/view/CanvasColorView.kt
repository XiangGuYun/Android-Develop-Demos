package com.androidui.huitu.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.activity.ContextUtils
import com.scwang.smartrefresh.layout.util.DensityUtil
import android.graphics.DashPathEffect



class CanvasColorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.BLUE)
    }

}