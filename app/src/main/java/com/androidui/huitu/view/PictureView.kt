package com.androidui.huitu.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Picture
import android.util.AttributeSet
import android.view.View

class PictureView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr){

    private var mPicture:Picture = Picture()
    private var mPaint = Paint()

    init {
        //一定要加上这句代码，否则有可能绘制不出来
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        mPaint.strokeWidth = 24f
        mPaint.style = Paint.Style.FILL
        mPaint.color = Color.RED
        mPaint.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas?) {
        recording()//如果要获取到控件绘制完成的宽高，该方法必须在此调用，否则获取不到。
        super.onDraw(canvas)
        canvas?.drawColor(Color.CYAN)
        //绘制方法1
//        mPicture.draw(canvas)
        //绘制方法2
        canvas?.drawPicture(mPicture)
    }

    private fun recording() {
        //开始录制
        val canvas = mPicture.beginRecording(width, height)
        println("width is $width  height is $height")
        canvas.drawCircle(mPicture.width/2f, mPicture.height/2f, 50f, mPaint)
        mPaint.color = Color.BLUE
        canvas.drawPoint(mPicture.width/2f, mPicture.height/2f, mPaint)
        mPicture.endRecording()
    }


}