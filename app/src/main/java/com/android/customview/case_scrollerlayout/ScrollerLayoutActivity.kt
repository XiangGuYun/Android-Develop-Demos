package com.android.customview.case_scrollerlayout

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_scroller_layout.*

@LayoutId(R.layout.activity_scroller_layout)
class ScrollerLayoutActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        header1.setRightClick {
            codeDialog.text("""
@Override
public boolean onInterceptTouchEvent(MotionEvent ev) {
    switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
            //记录最近的按下触摸点
            mXDown = ev.getRawX();
            //初始化最近的滑动触摸点
            mXLastMove = mXDown;
            break;
        case MotionEvent.ACTION_MOVE:
            //记录当前的X滑动触摸点
            mXMove = ev.getRawX();
            //计算X轴滑动的距离
            float diff = Math.abs(mXMove - mXDown);
            //刷新最近的X轴滑动触摸点
            mXLastMove = mXMove;
            // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
            if (diff > mTouchSlop) {
                return true;
            }
            break;
    }
    return super.onInterceptTouchEvent(ev);
}
            """.trimIndent())
        }

        header2.setRightClick {
            codeDialog.text("""
@Override
public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
        case MotionEvent.ACTION_MOVE:
            mXMove = event.getRawX();
            int scrolledX = (int) (mXLastMove - mXMove);
            if (getScrollX() + scrolledX < leftBorder) {
                scrollTo(leftBorder, 0);
                return true;
            } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                scrollTo(rightBorder - getWidth(), 0);
                return true;
            }
            scrollBy(scrolledX, 0);
            mXLastMove = mXMove;
            break;
        case MotionEvent.ACTION_UP:
            // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
            int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
            int dx = targetIndex * getWidth() - getScrollX();
            // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
            mScroller.startScroll(getScrollX(), 0, dx, 0);
            invalidate();
            break;
    }
    return super.onTouchEvent(event);
}

@Override
public void computeScroll() {
    // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
    if (mScroller.computeScrollOffset()) {
        scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        invalidate();
    }
}
            """.trimIndent())
        }
    }
}
