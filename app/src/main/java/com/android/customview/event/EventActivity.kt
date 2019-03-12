package com.android.customview.event

import android.os.Bundle
import com.android.R
import com.android.customview.event.case1.TestVPActivity
import com.android.customview.event.case2.EventCase2Activity
import com.android.supportlib.recyclerview.RVTest3Activity
import com.android.systemui.SurfaceViewActivity
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_event.*

@LayoutId(R.layout.activity_event)
class EventActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        btn1.click { go(TestVPActivity::class.java) }

        header.setLeftClick {
            codeDialog.text("""
现在以“ScrollView中的画板(SurfaceView)”为例，来思考这个问题：
①返回true：ScrollView完全拦截掉了画板的触摸事件，画板无法绘制任何东西。
②返回super.onInterceptTouchEvent：ScrollView部分拦截掉了画板的触摸事件，当触摸点存在Y轴方向变化时，会拖动ScrollView，导致绘制很不流畅。
③返回false：ScrollView将触摸事件完全分发给画板处理，当在画板上绘制时，不会滑动ScrollView。

下面的案例将返回false。
            """.trimIndent())
        }

        btn.click { go(SurfaceViewActivity::class.java) }

        header1.setLeftClick {
            codeDialog.text("""
该案例的Activity中的ViewPager中有两个Fragment，第一个Fragment中也有一个ViewPager,ViewPager中有两张图片。
现在要实现的效果就是这样，拖动子ViewPager不会滑向另外一个Fragment。

总结：要实现这样效果，父ViewPager需要拦截掉子ViewPager的触摸事件，防止子ViewPager向另外一个Fragment的滑动事件。

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

        header2.setLeftClick {
            codeDialog.text("""
如何在左右滑动列表项的时候禁止上下滑动列表？

思路：需要重写RecyclerView的onInterceptTouchEvent方法，在ACTION_MOVE中拦截掉RecyclerView的滑动事件。

var xDistance: Float = 0f//Y轴移动距离
var yDistance: Float = 0f//X轴移动距离
var xLast: Float = 0f//最近触摸点的X坐标
var yLast: Float = 0f//最近触摸点的Y坐标

override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
    when(e?.action){
        MotionEvent.ACTION_DOWN->{
            //重置移动距离
            yDistance = 0f
            xDistance = 0f
            //记录最近的触摸点
            xLast = e.x
            yLast = e.y
        }
        MotionEvent.ACTION_MOVE->{
            //当前滑动到的点
            val curX = e.x
            val curY = e.y

            //计算滑动距离
            xDistance += Math.abs(curX - xLast)
            yDistance += Math.abs(curY - yLast)

            //刷新最近的触摸点
            xLast = curX
            yLast = curY

            //如果在X轴滑动距离大于Y轴滑动距离，便进行拦截
            if(xDistance > yDistance){
                return false
            }
        }
    }
    return super.onInterceptTouchEvent(e)
}
            """.trimIndent())
        }

        header3.setLeftClick {
            codeDialog.text("""
父容器的onTouchEvent返回false
红色返回super.onTouchEvent：只能触发父容器和子View的ACTION_DOWN事件。
绿色返回true：子View完全消费了触摸事件，可以产生ACTION_DOWN，ACTION_MOVE，ACTION_UP三个事件。
蓝色返回false：只能触发父容器和子View的ACTION_DOWN事件。

父容器的onTouchEvent返回true
红色返回super.onTouchEvent：可以触发父容器ACTION_DOWN，ACTION_MOVE，ACTION_UP和子View的ACTION_DOWN事件。
绿色返回true：子View完全消费了触摸事件，可以产生ACTION_DOWN，ACTION_MOVE，ACTION_UP三个事件。
蓝色返回false：可以触发父容器ACTION_DOWN，ACTION_MOVE，ACTION_UP和子View的ACTION_DOWN事件。

另外在ScrollView中如果View的onTouchEvent返回了true还可以触发ACTION_CANCEL事件，表示触摸事件被ScrollView的滑动事件中断。

总结：当onTouchEvent返回true表示由当前View消费掉所有能触发的触摸事件，其它层级的View只能触发ACTION_DOWN事件；
    当onTouchEvent返回false表示叫触摸事件交给上一层级的View处理，自己只触发ACTION_DOWN事件。
    因此便可以理解为什么onTouchEvent默认返回的是false。
            """.trimIndent())
        }

        btn4.click { go(EventCase2Activity::class.java) }

    }

}
