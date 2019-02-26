package com.androidui.gesture.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.androidui.R

class VelocityTrackImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ImageView(context, attrs, defStyleAttr){

    var velocityTracker: VelocityTracker? = VelocityTracker.obtain()
    var tvLog:TextView? = null

    private var mPointerId: Int = 0

    private var mMaxVelocity: Float = 0f

    init {
        //Maximum velocity to initiate a fling, as measured in pixels per second
        mMaxVelocity = ViewConfiguration.get(context).scaledMaximumFlingVelocity.toFloat()  //注意这里返回的都是每秒的像素单位
        ViewConfiguration.get(context).scaledTouchSlop//获得能够视为是手势滑动的最短距离(slop意为溢出，即只有触摸范围溢出这个值，才算是滑动)
        ViewConfiguration.get(context).scaledMinimumFlingVelocity//获得允许执行一个fling手势动作的最小速度值
        ViewConfiguration.get(context).scaledMaximumFlingVelocity//获得允许执行一个fling手势动作的最大速度值
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
                recordInfo(velocityTracker!!.getXVelocity(mPointerId), velocityTracker!!.getYVelocity(mPointerId))
            }
            MotionEvent.ACTION_UP->{
                releaseVelocityTracker()
            }
            MotionEvent.ACTION_CANCEL->{
                releaseVelocityTracker()
            }
        }
        return true
    }

    fun releaseVelocityTracker() {
        velocityTracker?.clear()
        velocityTracker?.recycle()
        velocityTracker = null
    }

    fun recordInfo(velocityX:Float, velocityY:Float) {
        val info = "velocityX is $velocityX\nvelocityY is $velocityY"
        Log.d("LiaBin", "info:$info")
        if(tvLog!=null){
            tvLog?.text = info
        }
    }

}