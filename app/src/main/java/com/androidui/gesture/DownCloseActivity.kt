package com.androidui.gesture

import android.os.Bundle
import android.view.GestureDetector
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import android.widget.Toast
import com.androidui.MainActivity
import android.content.Intent
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_down_close.*

@LayoutId(R.layout.activity_down_close)
class DownCloseActivity : KotlinActivity() {

    private lateinit var detector: GestureDetector
    private lateinit var listener:CloseGestureListener
    private val MIN_MOVE = 200

    override fun init(bundle: Bundle?) {
        btnCode.click {
            codeDialog.text("""
private lateinit var detector: GestureDetector
private lateinit var listener:CloseGestureListener
private val MIN_MOVE = 200


override fun init(bundle: Bundle?) {
    listener = CloseGestureListener()
    detector = GestureDetector(this, listener)
}

override fun onTouchEvent(event: MotionEvent?): Boolean {
    return detector.onTouchEvent(event)
}

private inner class CloseGestureListener : GestureDetector.SimpleOnGestureListener() {

    override fun onFling(e1: MotionEvent, e2: MotionEvent, v: Float, v1: Float): Boolean {
        if (e1.y - e2.y > MIN_MOVE) {
            startActivity(Intent(this@DownCloseActivity, NewActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            "通过手势启动Activity".toast()
        } else if (e1.y - e2.y < MIN_MOVE) {
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            "通过手势关闭Activity".toast()
        }
        return true
    }
}
            """.trimIndent())
        }
        listener = CloseGestureListener()
        detector = GestureDetector(this, listener)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return detector.onTouchEvent(event)
    }

    private inner class CloseGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onFling(e1: MotionEvent, e2: MotionEvent, v: Float, v1: Float): Boolean {
            if (e1.y - e2.y > MIN_MOVE) {
                startActivity(Intent(this@DownCloseActivity, NewActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                "通过手势启动Activity".toast()
            } else if (e1.y - e2.y < MIN_MOVE) {
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                "通过手势关闭Activity".toast()
            }
            return true
        }
    }

}
