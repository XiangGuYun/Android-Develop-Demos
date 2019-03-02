package com.androidui.customview.drag

import android.os.Bundle
import android.support.v4.widget.ViewDragHelper
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_view_drag_helper.*

/**
 * https://blog.csdn.net/briblue/article/details/73730386
 * https://www.jianshu.com/p/111a7bc76a0e
 */
@LayoutId(R.layout.activity_view_drag_helper)
class ViewDragHelperActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        doBtn()
        doTV()
        doHeader()
    }

    private fun doHeader() {
        header1.setRightClick {
            codeDialog.text("""
private var dragHelper: ViewDragHelper
var tv1:TextView?=null//记录tryCaptureView参数
var tv2:TextView?=null//记录clampViewPositionHorizontal参数
var tv3:TextView?=null//记录clampViewPositionVertical参数

init {
    dragHelper = ViewDragHelper.create( this, object :ViewDragHelper.Callback(){
        //回调函数1
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            tv1?.text = "child's tag is $‘{child.tag}, pointerId is $’pointerId"
            return true
        }
        //回调函数1
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            tv2?.text = "child's tag is $’{child.tag}, left is $’left, dx is $‘dx"
            return left
        }
        //回调函数1
        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            tv3?.text = "child's tag is $‘{child.tag}, top is $’top, dy is $‘dy"
            return top
        }
    })
}

override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
    //交给ViewDragHelper处理
    return dragHelper.shouldInterceptTouchEvent(ev!!)
}

override fun onTouchEvent(event: MotionEvent?): Boolean {
    //交给ViewDragHelper处理
    dragHelper.processTouchEvent(event!!)
    return true
}

            """.trimIndent())
        }
        header3.setRightClick {
            codeDialog.text("""
override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
    if (releasedChild == viewTest)
    {
        dragHelper?.smoothSlideViewTo(viewTest,0,0)
        //dragHelper?.settleCapturedViewAt(0, 0)
        invalidate()
    }
}

override fun computeScroll() {
    if (dragHelper?.continueSettling(true)!!)
    {
        invalidate()
    }
}
            """.trimIndent())
        }
    }

    private fun doTV() {
        tvResult.setText("""
DEMO分析结果

override fun tryCaptureView(child: View, pointerId: Int): Boolean
    该函数控制哪些子View可以被拖拽，可以对child的id进行switch，并对需要拖拽的child返回true，对不需要拖拽的child返回false。

override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int
    该函数可以使用left参数来限制子View的横向滑动范围，dx参数描述了横向拖拽的速度。

override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int
    该函数可以使用top参数来限制子View的纵向滑动范围，dy参数描述了纵向拖拽的速度。
        """.trimIndent())

        tv2.text = """
接下来，我们来对这三个函数做点修改：
1.只有DragMe1能够被拖拽。
2.DragMe1横向拖拽的范围是父容器的一半。
3.DragMe1纵向拖拽的范围是父容器的一半。
        """.trimIndent()

        tv3.text = """
使用setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT)设置ViewGroup左边缘才可以被拖拽，同时在ViewDragHelper.Callback的onEdgeDragStarted方法中，使用dragHelper.captureChildView主动去捕获子TextView。
        """.trimIndent()
    }

    private fun doBtn() {
        btnViewResult1.click {
            go(Case1Activity::class.java)
        }
        btn2.click {
            go(Case2Activity::class.java)
        }
        btn3.click {
            go(Case3Activity::class.java)
        }
        btn4.click {
            go(Case4Activity::class.java)
        }
    }

}
