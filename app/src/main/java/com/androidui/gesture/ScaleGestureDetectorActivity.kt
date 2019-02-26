package com.androidui.gesture

import android.os.Bundle
import android.view.ScaleGestureDetector
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_scale_gesture_detector.*

@LayoutId(R.layout.activity_scale_gesture_detector)
class ScaleGestureDetectorActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        headerMethod.setLeftClick {
            webDialog.url("gesture/scale_gesture")
        }.setRightClick {
            codeDialog.text("""
listener = ScaleGestureDetector(context, object :ScaleGestureDetector.OnScaleGestureListener{
        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {}

        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            println(detector?.scaleFactor)
            println(detector?.currentSpan)
            println(detector?.currentSpanX)
            println(detector?.currentSpanY)
            println(detector?.focusX)
            println(detector?.focusY)
            println(detector?.previousSpan)
            println(detector?.previousSpanX)
            println(detector?.previousSpanY)
            println(detector?.eventTime)
            println(detector?.timeDelta)
            println(detector?.isInProgress)
            return true
        }
    })

override fun onTouchEvent(event: MotionEvent?): Boolean {
    return listener.onTouchEvent(event)
}
            """.trimIndent())
        }
        iv1.tvLog = tvLog1
        iv1.tvState = tvState
    }
}
