package com.androidui.gesture

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_drag_image.*

@LayoutId(R.layout.activity_drag_image)
class DragImageActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        btnViewCode1.click {
            codeDialog.text("""
private var listener = DragGestureListener()
private var detector: GestureDetector
var circleX = 100f
var circleY = 100f
var paint = Paint()

init {
    setBackgroundColor(Color.CYAN)
    paint.color = Color.RED
    detector = GestureDetector(context, listener)
}

override fun onTouchEvent(ev: MotionEvent?): Boolean {
    detector.onTouchEvent(ev)
    // 一定要返回true，不然获取不到完整的事件
    // 如果是Activity则可以直接返回detector.onTouchEvent(ev)
    return true
}

override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    canvas?.drawCircle(circleX, circleY, 50f, paint)
}

inner class DragGestureListener: GestureDetector.SimpleOnGestureListener(){

    override fun onDown(ev: MotionEvent?): Boolean {
        if(ev!=null){
            circleX = ev.x
            circleY = ev.y
            invalidate()
        }
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        circleX -= distanceX
        circleY -= distanceY
        invalidate()
        return false
    }

}
            """.trimIndent())
        }

        btnViewCode2.click {
            codeDialog.text("""
private var listener = DragGestureListener()
private var detector: GestureDetector
var paint = Paint()
private var bmp: Bitmap
var mMatrix = Matrix()
var cX = 0f
var cY = 0f

init {
    setBackgroundColor(Color.BLACK)
    bmp = BitmapFactory.decodeResource(resources, R.mipmap.love)
    paint.color = Color.RED
    detector = GestureDetector(context, listener)
}

override fun onTouchEvent(ev: MotionEvent?): Boolean {
    detector.onTouchEvent(ev)
    // 一定要返回true，不然获取不到完整的事件
    // 如果是Activity则可以直接返回detector.onTouchEvent(ev)
    return true
}

override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    canvas?.drawBitmap(bmp, mMatrix, null)
}

inner class DragGestureListener: GestureDetector.SimpleOnGestureListener(){

    override fun onDown(ev: MotionEvent?): Boolean {
        if(ev!=null){
            cX = ev.x
            cY = ev.y
            mMatrix.setTranslate(ev.x, ev.y)
            invalidate()
        }
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        cX -= distanceX
        cY -= distanceY
        mMatrix.setTranslate(cX, cY)
        invalidate()
        return false
    }

}
            """.trimIndent())
        }
    }
}
