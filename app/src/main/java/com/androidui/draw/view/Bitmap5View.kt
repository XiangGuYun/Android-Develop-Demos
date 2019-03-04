package com.androidui.draw.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.androidui.R
import com.kotlinlib.activity.ContextUtils
import com.kotlinlib.view.surfaceview.DrawView


class Bitmap5View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        DrawView(context, attrs, defStyleAttr), ContextUtils{

    var isDrawingPicture = true
    val paint = Paint()
    private var bitmap: Bitmap
    private var srcList = ArrayList<Rect>()
    private var dstList = ArrayList<Rect>()
    private var rectList = ArrayList<Rect>()

    init {
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.img3)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        for (i in 0 until  100){
            srcList.add(Rect(0,0,i*(1000 /100),bitmap.height))
            dstList.add(Rect(0,0,i*(1000/100),bitmap.height))
            rectList.add(Rect(0,0,i*100-i*(1000/100),bitmap.height))
        }
    }

    override fun drawing(canvas: Canvas, index: Int) {
        if(isDrawingPicture){
            canvas.drawBitmap(bitmap, srcList[i], dstList[i], null)
        }else{
            canvas.drawRect(srcList[i], paint)
        }

        if(i==99){
            i = 0
            isDrawingPicture = !isDrawingPicture
        }
    }



}