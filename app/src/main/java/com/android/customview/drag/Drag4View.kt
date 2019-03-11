package com.android.customview.drag

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.android.R
import com.kotlinlib.other.BaseInterface
import com.kotlinlib.other.DensityUtils
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils


class Drag4View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr), BaseInterface{

    private var dragHelper: ViewDragHelper?=null
    private var paint = Paint()
    init {
        paint.color = Color.RED
        dragHelper = ViewDragHelper.create( this, object :ViewDragHelper.Callback(){
            //指定捕捉那些子View可以被拖拽
            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return child == viewTest
            }
            //禁止左右滑动
            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                return 0
            }
            //限制上下滑动的范围
            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                if(top<DensityUtils.dip2px(context, 0f)){
                    return DensityUtils.dip2px(context, 0f)//禁止超过上边界
                }
                if(top>height){
                    return height//禁止超过下边界
                }
                return top
            }
            //释放后让子View滑回指定位置
            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                if (releasedChild == viewTest)
                {
                    dragHelper?.smoothSlideViewTo(viewTest,0, DensityUtils.dip2px(context, 80f))
                    invalidate()
                }
            }

        })

    }

    lateinit var viewTest:View

    override fun computeScroll() {
        if (dragHelper?.continueSettling(true)!!)
        {
            invalidate()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        viewTest = findViewById(R.id.tvDragMe)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return dragHelper?.shouldInterceptTouchEvent(ev!!)!!
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        dragHelper?.processTouchEvent(event!!)
        return true
    }


}
