package com.android.draw.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.android.R
import com.kotlinlib.activity.ContextUtils


class Bitmap3View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), ContextUtils{

    val paint = Paint()

    init {
        paint.textSize = 30f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.CYAN)

        val bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.girl1)

        val src = Rect(0, 0, bitmap.width / 2, bitmap.height)

        // 指定图片在屏幕上显示的区域
        val dst = Rect(100, 100, 250, 250)

        // 绘制图片
        canvas?.drawBitmap(bitmap, src, dst, null)
        listOf("val src = Rect(0, 0, bitmap.width / 2, bitmap.height), 100f, 100f, Paint())",
                "val dst = Rect(100, 100, 250, 250)",
                "canvas?.drawBitmap(bitmap, src, dst, null)")
                .forEachIndexed {i,it->
                    canvas?.drawText(it, 15f, height/2f+i*paint.getTextRect(it).height(), paint)
                }
    }

}