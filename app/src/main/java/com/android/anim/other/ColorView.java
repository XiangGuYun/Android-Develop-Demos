package com.android.anim.other;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ColorView extends View {
    private Paint mPaint;// 绘图画笔
    private String color ="#ff1234";
    private Context ctx;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    // 构造方法(初始化画笔)
    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始化画笔
        ctx = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor(color));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth()/2f, getHeight()/2f, getWidth()/2f, mPaint);
    }

}