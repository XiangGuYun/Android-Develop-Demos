package com.android.customview.event.case1;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {
    private int mFirstX =0,mFirstY=0;
    private String TAG = "MyViewPager";

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if(this.getCurrentItem()==2) //如果滑动到了第三个Fragment
        {
            boolean isIntercept=false;
            int x=(int) event.getX();
            int y=(int) event.getY();
            Log.e(TAG, "onInterceptTouchEvent");
            switch (event.getAction()) {
   /**
    * 父容器必须返回false，即不拦截ACTION_DOWN事件，
    * 否则后续的ACTION_MOVE，ACTION_UP事件都会直接交给父容器处理，
    * 事件没办法再传递给子元素了
    */
            case MotionEvent.ACTION_DOWN:                
                Log.e(TAG, "onInterceptTouchEvent_ACTION_DOWN");
                isIntercept=false;                 
                break;
     /**
     * 根据需要觉定是否拦截
     */
            case MotionEvent.ACTION_MOVE: if (Math.abs(x - mFirstX) > Math.abs(y - mFirstY)) //左右滑动
                {
                        isIntercept = true;
//                        if(正在下拉) //想刷新时候(若不写这一步，如果我们向下滑到一半突然左右滑动那么listView就会卡在中间状态不动。)
//                            isIntercept=false;
                } 
                else //上下滑动
                {
                        isIntercept = false;
                }
                Log.e(TAG, "onInterceptTouchEvent_ACTION_MOVE");
                break;
     /**
     * 必须返回false，因为ACTION_UP本身没有太大意义。
     * 
     */
            case MotionEvent.ACTION_UP: 
                isIntercept=false;
                Log.e(TAG, "onInterceptTouchEvent_ACTION_UP");
                break;
            default:
                break;
            }

            mFirstX=x;
            mFirstY=y;
            Log.e(TAG, "onInterceptTouchEvent_return");
            return isIntercept;
        }
        else //如果没有滑动到了第三个Fragment，不作处理
            return super.onInterceptTouchEvent(event);
    }
}