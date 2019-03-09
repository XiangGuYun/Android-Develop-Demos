package com.android.gesture

import android.os.Bundle

import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_image_scale.*

@LayoutId(R.layout.activity_image_scale)
class ImageScaleActivity : KotlinActivity(){

    override fun init(bundle: Bundle?) {
        btnViewCode.click {
            codeDialog.text("""
val mMatrix = Matrix()
var currentScale = 1f//当前的缩放率
var maxScale = 5f//最大的缩放率
var minScale = 0.1f//最小的缩放率
val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.img3)

init {
    setBackgroundColor(Color.BLACK)
}

//缩放监听
var listener = ScaleGestureDetector(context,
        object :ScaleGestureDetector.SimpleOnScaleGestureListener(){
            override fun onScale(detector: ScaleGestureDetector?): Boolean {
                if(detector!=null&&currentScale*detector.scaleFactor>=minScale&&currentScale*detector.scaleFactor<=maxScale){
                    mMatrix.preTranslate(width/2f,height/2f)
                    mMatrix.setScale(currentScale*detector.scaleFactor, currentScale*detector.scaleFactor, bitmap.width/2f, bitmap.height/2f)
                    invalidate()
                    currentScale *= detector.scaleFactor
                }
                return true
            }
        })

//双击监听
val detector =GestureDetector(object :GestureDetector.SimpleOnGestureListener(){
    override fun onDoubleTap(e: MotionEvent?): Boolean {
        currentScale = 1f
        mMatrix.setScale(1f, 1f, bitmap.width/2f, bitmap.height/2f)
        invalidate()
        return false
    }


})

override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    canvas?.translate(width/2f-bitmap.width/2,height/2f-bitmap.height/2)
    canvas?.scale(width/bitmap.width.toFloat(),width/bitmap.width.toFloat(), bitmap.width/2f,bitmap.height/2f)
    canvas?.drawBitmap(bitmap, mMatrix, null)
}

override fun onTouchEvent(event: MotionEvent?): Boolean {
    listener.onTouchEvent(event)
    detector.onTouchEvent(event)
    return true
}
            """.trimIndent())
        }
    }

}