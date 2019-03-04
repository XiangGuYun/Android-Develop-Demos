package com.androidui.draw.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.androidui.R
import com.kotlinlib.activity.ContextUtils


class Bitmap4View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint = Paint()

    private var bitmap: Bitmap

    private lateinit var src: Rect
    private lateinit var dst1: Rect
    private lateinit var dst2: Rect
    private lateinit var dst3: Rect
    private lateinit var dst4: Rect
    private lateinit var dst5: Rect

    init {
        paint.textSize = 30f
        bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.girl)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        src = Rect(0, 0, 100, 100)
        // 指定图片在屏幕上显示的区域
        dst1 = Rect(0, 0, 250, 250)
        Log.d("Teasdad", "width is $width, height is $height")
        dst2 = Rect(width-250, 0, width, 250)
        dst3 = Rect(0, height-250, 250, height)
        dst4 = Rect(width-250, height-250, width, height)
        dst5 = Rect((width - 250)/2, (height - 250)/2, (width + 250)/2, (height + 250)/2)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.CYAN)
        // 绘制图片
        canvas?.drawBitmap(bitmap, src, dst1, null)
        canvas?.drawBitmap(bitmap, src, dst2, null)
        canvas?.drawBitmap(bitmap, src, dst3, null)
        canvas?.drawBitmap(bitmap, src, dst4, null)
        canvas?.drawBitmap(bitmap, src, dst5, null)
    }

}