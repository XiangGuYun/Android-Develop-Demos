package com.kotlinlib.other;

import android.view.View;

/**
 * Created by Administrator on 2018/3/14 0014.
 */

public class WrapperView {

    private View target;

    public WrapperView(View target){
        this.target = target;
    }

    public int getHeight(){
        return target.getLayoutParams().height;
    }

    public void setHeight(int height){
        target.getLayoutParams().height = height;
        target.requestLayout();
    }

    public int getWidth(){
        return target.getLayoutParams().width;
    }

    public void setWidth(int width){
        target.getLayoutParams().width = width;
        target.requestLayout();
    }
}
