package com.android.gesture.view

import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.*
import android.widget.ImageView
import android.widget.TextView

class ScaleImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ImageView(context, attrs, defStyleAttr){

    var velocityTracker: VelocityTracker? = VelocityTracker.obtain()
    var listener:ScaleGestureDetector
    var mPointerId: Int = 0
    var mMaxVelocity: Float = 0f
    val mMatrix = Matrix()
    var velX = 0f
    var velY = 0f
    var tvLog:TextView? = null
    var tvState:TextView? = null

    init {
        mMaxVelocity = ViewConfiguration.get(context).scaledMaximumFlingVelocity.toFloat()
        listener = ScaleGestureDetector(context, object :ScaleGestureDetector.OnScaleGestureListener{
            override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
                tvState?.text = "ScaleBegin"
                return true
            }

            override fun onScaleEnd(detector: ScaleGestureDetector?) {
                tvState?.text = "ScaleEnd"
        }

            override fun onScale(detector: ScaleGestureDetector?): Boolean {
                tvState?.text = "Scale进行中" +
                        ""
                println("scaleFactor is ${detector?.scaleFactor}, currentSpan is ${detector?.currentSpan}, " +
                        "currentSpanX is ${detector?.currentSpanX}, currentSpanY is ${detector?.currentSpanY}")
                tvLog?.text = "scaleFactor is ${detector?.scaleFactor}\ncurrentSpan is ${detector?.currentSpan}\n" +
                        "currentSpanX is ${detector?.currentSpanX}\ncurrentSpanY is ${detector?.currentSpanY}\n"+
                        "focusX is ${detector?.focusX}\n"+
                        "focusY is ${detector?.focusY}\n"+
                        "previousSpan is ${detector?.previousSpan}\n"+
                        "previousSpanX is ${detector?.previousSpanX}\n"+
                        "previousSpanY is ${detector?.previousSpanY}\n"+
                        "eventTime is ${detector?.eventTime}\n"+
                        "timeDelta is ${detector?.timeDelta}\n"+
                        "isInProgress is ${detector?.isInProgress}"
                if(detector!!.scaleFactor < 2f){
                    return false
                }
                mMatrix.preScale(detector.scaleFactor, detector.scaleFactor)
                imageMatrix = mMatrix
                return true
            }
        })

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        velocityTracker?.addMovement(event)
        when(event!!.action){
            MotionEvent.ACTION_DOWN->{
                //求第一个触点的id， 此时可能有多个触点，但至少一个
                mPointerId = event.getPointerId(0)
            }
            MotionEvent.ACTION_MOVE->{
                //求伪瞬时速度  如果速度小于mMaxVelocity，正常显示。如果大于mMaxVelocity，则显示mMaxVelocity
                if(velocityTracker==null){
                    velocityTracker = VelocityTracker.obtain()
                }
                velocityTracker?.computeCurrentVelocity(1000, mMaxVelocity)
                //1000表示像素/秒，1表示像素/毫秒。因为mMaxVelocity是用像素/秒做单位，所以此时用1000
                //getXVelocity getYVelocity之前必须先调用computeCurrentVelocity
                velX = velocityTracker!!.getXVelocity(mPointerId)
                velY = velocityTracker!!.getYVelocity(mPointerId)
            }
            MotionEvent.ACTION_UP->{
                releaseVelocityTracker()
            }
            MotionEvent.ACTION_CANCEL->{
                releaseVelocityTracker()
            }
        }
        return listener.onTouchEvent(event)
    }

    fun releaseVelocityTracker() {
        velocityTracker?.clear()
        velocityTracker?.recycle()
        velocityTracker = null
    }

}