package com.android.customview.event

import android.os.Bundle
import com.android.R
import com.android.customview.event.case1.TestVPActivity
import com.android.supportlib.recyclerview.RVTest3Activity
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_event.*

@LayoutId(R.layout.activity_event)
class EventActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        btn1.click { go(TestVPActivity::class.java) }

        header1.setLeftClick {
            codeDialog.text("""
这是一个很有意思的案例。可以看到案例Activity中的ViewPager中有两个Fragment，第一个Fragment中有一个ViewPager,ViewPager中有两张图片。
现在要实现的效果就是这样，拖动ViewPager不会拖向另外一个Fragment。那么子ViewPager是如何拦截父ViewPager的触摸时间的？
var canScrollToNext = true//当ViewPager滑到最后一页时，该值为false
var lastX = 0
var lastY = 0
var isInter = false//是否拦截

override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
    //记录当前的触摸坐标
    val x = ev?.x?.toInt()!!
    val y = ev?.y?.toInt()!!
    when(ev.action){
        //按下事件不需要拦截
        MotionEvent.ACTION_DOWN->isInter = super.dispatchTouchEvent(ev)
        //抬起事件不需要拦截
        MotionEvent.ACTION_UP->isInter = super.dispatchTouchEvent(ev)
        //移动事件需要拦截
        MotionEvent.ACTION_MOVE->{

            if(DensityUtils.dip2px(context,200f) >= y//确定当前触摸的位置在ViewPager上
                    &&!canScrollToNext//确定ViewPager已经滑到最后一页
                    &&x<lastX//确定用户的操作是向右滑
            ){
                isInter = true//满足以上三个条件就拦截
            }
            else
            {
                isInter = super.dispatchTouchEvent(ev)//不拦截
            }

        }
        else->isInter = super.dispatchTouchEvent(ev)
    }
    //记录上次的触摸坐标
    lastX = x
    lastY = y
    return isInter
}

            """.trimIndent())
        }

        btn2.click { go(RVTest3Activity::class.java) }
    }

}
