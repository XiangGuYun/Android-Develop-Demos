package com.android.customview.slide_mothod.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Scroller;
   
public class CustomView extends FrameLayout {

    private Scroller mScroller;   
   
    public CustomView(Context context, AttributeSet attrs) {   
        super(context, attrs);   
        mScroller = new Scroller(context);   
    }   
   
    //调用此方法滚动到目标位置   
    public void smoothScrollTo(int fx, int fy, int duration) {
        int dx = fx - mScroller.getFinalX();   
        int dy = fy - mScroller.getFinalY();   
        smoothScrollBy(dx, dy, duration);
    }   
   
    //调用此方法设置滚动的相对偏移   
    public void smoothScrollBy(int dx, int dy, int duration) {
        Log.d("CurrentThread", "CurrentThread1 is "+Thread.currentThread().getName());
        //设置mScroller的滚动偏移量   
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy, duration);
        invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果   
    }   
       
    @Override   
    public void computeScroll() {
        super.computeScroll();
        //先判断mScroller滚动是否完成
        if (mScroller.computeScrollOffset()) {
            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            Log.d("CurrentThread", "CurrentThread2 is "+Thread.currentThread().getName());
            //必须调用该方法，否则不一定能看到滚动效果   
            postInvalidate();
        }
    }   
}   