package com.android.supportlib.recyclerview

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class MyTouchCallback(var touch:TouchCallbackListener): ItemTouchHelper.Callback() {

    interface TouchCallbackListener {
        /**
         * 长按拖拽时的回调
         * @param fromPosition 拖拽前的位置
         * @param toPosition 拖拽后的位置
         */
        fun onItemMove(fromPosition: Int, toPosition: Int)

        /**
         * 滑动时的回调
         * @param position 滑动的位置
         */
        fun onItemSwipe(position: Int)
    }

    /**
     * 定义列表可以怎样滑动（上下左右）
     * @param p0 RecyclerView
     * @param p1 RecyclerView.ViewHolder
     * @return Int
     */
    override fun getMovementFlags(p0: RecyclerView, p1: RecyclerView.ViewHolder): Int {
        //上下滑动
        val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        //左右滑动
        val swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        //使用此方法生成标志返回
        return makeMovementFlags(dragFlag, swipeFlag)
    }

    /**
     * 拖拽移动时调用的方法
     * @param p0 RecyclerView
     * @param p1 RecyclerView.ViewHolder
     * @param p2 RecyclerView.ViewHolder
     * @return Boolean
     */
    override fun onMove(rv: RecyclerView, holder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        touch.onItemMove(holder.adapterPosition, target.adapterPosition)
        return true
    }

    /**
     * 滑动时调用的方法
     * @param p0 RecyclerView.ViewHolder
     * @param p1 Int
     */
    override fun onSwiped(holder: RecyclerView.ViewHolder, p1: Int) {
        touch.onItemSwipe(holder.adapterPosition)
    }

    /**
     * 是否允许长按拖拽
     * @return Boolean
     */
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    /**
     * 是否允许滑动
     * @return Boolean
     */
    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }
}