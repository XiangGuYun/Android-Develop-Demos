package com.androidui.gesture

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_velocity_tracker.*

@LayoutId(R.layout.activity_velocity_tracker)
class VelocityTrackerActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        read1.setText("""
当你需要跟踪触摸屏事件的速度的时候,使用obtain()方法来获得VelocityTracker类的一个实例对象
在onTouchEvent回调函数中，使用addMovement(MotionEvent)函数将当前的移动事件传递给VelocityTracker对象
使用computeCurrentVelocity (int units)函数来计算当前的速度，使用getXVelocity ()、 getYVelocity ()函数来获得当前的速度
        """.trimIndent())

        iv1.tvLog = tv1

        header1.setRightClick {
            codeDialog.text("""
var velocityTracker: VelocityTrackerActivity? = VelocityTrackerActivity.obtain()
var mPointerId: Int = 0
var mMaxVelocity: Float = 0f
var tvLog:TextView? = null

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
                velocityTracker = VelocityTrackerActivity.obtain()
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
    val info = "velocityX is $'velocityX\nvelocityY is $'velocityY"
    Log.d("LiaBin", "info:$'info")
    if(tvLog!=null){
        tvLog?.text = info
    }
}
            """.trimIndent())
        }

    }
}
