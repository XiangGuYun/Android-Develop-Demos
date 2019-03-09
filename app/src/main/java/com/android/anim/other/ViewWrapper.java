package com.android.anim.other;

import android.view.View;

// 提供ViewWrapper类,用于包装View对象
// 本例:包装Button对象
public class ViewWrapper {

    private View mTarget;

    // 构造方法:传入需要包装的对象
    public ViewWrapper(View target) {
        mTarget = target;
    }

    // 为宽度设置get（） & set（）
    public int getWidth() {
        return mTarget.getLayoutParams().width;
    }

    public void setWidth(int width) {
        mTarget.getLayoutParams().width = width;
        mTarget.requestLayout();
    }

}