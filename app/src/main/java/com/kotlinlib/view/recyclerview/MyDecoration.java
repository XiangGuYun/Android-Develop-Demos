package com.kotlinlib.view.recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MyDecoration extends RecyclerView.ItemDecoration{
    /**
     * step1 获得条目的偏移量 ---- 就相当于空出一块矩形空间 会在矩形空间里进行绘制
     * 目标针对每一个item个体
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
 
    /**
     * step2 在item间绘制  常用于绘制分割线
     * 针对整个Recyclerview 绘制需要循环遍历item子布局 然后方能针对具体的item进行增加绘制
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
 
    /**
     * 在item上绘制
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
