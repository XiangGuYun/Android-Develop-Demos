package com.kotlinlib.view.recyclerview;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;

import java.util.Collections;
import java.util.List;

/**
 * RecyclerView工具类
 */
public class RVUtils {

    public RecyclerView rv;
    public Context context;
    private EasyRVAdapter adapter;
    public List dataList;

    public ItemTouchHelper getmItemTouchHelper() {
        return mItemTouchHelper;
    }

    private ItemTouchHelper mItemTouchHelper;

    public RVUtils(RecyclerView recyclerView) {
        rv = recyclerView;
        context = recyclerView.getContext();
    }

    /**
     * 滑动到RV的指定位置
     */
    public void scrollToPosition(int position, List list) {
        if (position >= 0 && position <= list.size() - 1) {
            int firstItem = ((LinearLayoutManager) rv.getLayoutManager()).findFirstVisibleItemPosition();
            int lastItem = ((LinearLayoutManager) rv.getLayoutManager()).findLastVisibleItemPosition();
            if (position <= firstItem) {
                rv.scrollToPosition(position);
            } else if (position <= lastItem) {
                int top = rv.getChildAt(position - firstItem).getTop();
                rv.scrollBy(0, top);
            } else {
                rv.scrollToPosition(position);
            }
        }
    }

    /**
     * 获取第一个可见的位置
     * @return
     */
    public int getFirstVisiblePosition(){
        LinearLayoutManager layoutManager = (LinearLayoutManager) rv.getLayoutManager();
        return layoutManager.findFirstVisibleItemPosition();

    }

     /**
     * 获取滑动方向Y轴的距离
     * @return
     */
    public int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) rv.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        android.view.View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

    /**
     * 支持可拖拽列表项
     * @param dataList 列表项数据源
     * @param needDisableSome 是否需要禁用某些列表项的拖拽
     * @return
     */
    public RVUtils enableDraggableItem(final List<?> dataList, final boolean needDisableSome){
        //创建列表项触摸助手
        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback(){
            /**
             * 必须实现的方法，设置是否滑动时间，以及拖拽的方向
             * @param recyclerView
             * @param viewHolder
             * @return
             */
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //判断是否是网格布局，是则上下左右都可拖动，否则只能上下拖动
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager||
                        recyclerView.getLayoutManager() instanceof  StaggeredGridLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }

            /**
             * 在拖动的时候不断回调的方法，需要将正在拖拽的item和集合的item进行交换元素，然后在通知适配器更新数据
             * @param recyclerView
             * @param viewHolder
             * @param target
             * @return
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(dataList, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(dataList, i, i - 1);
                    }
                }
                rv.getAdapter().notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            /**
             * onSwiped是替换后调用的方法，可以不用管。
             * @param viewHolder
             * @param direction
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            /**
             * 长按选中Item的时候开始调用
             * 在选中的时候设置高亮背景色，在完成的时候移除高亮背景色
             * @param viewHolder
             * @param actionState
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
//                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
//                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
//                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            /**
             * 手指松开的时候还原
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
//                viewHolder.itemView.setBackgroundColor(0);
            }

            /**
             * 重写拖拽不可用
             * @return
             */
            @Override
            public boolean isLongPressDragEnabled() {
                return !needDisableSome;
            }

        });
        mItemTouchHelper.attachToRecyclerView(rv);
        return this;
    }

    /**
     * 删除操作
     * @param index
     * @param itemCount
     */
    public void doDelete(int index, int itemCount) {
        adapter.remove(index);
        adapter.notifyItemRangeRemoved(0, itemCount);
    }

    /**
     * 设置网格列表
     * @param spanCount  列数
     * @param isVertical 是否垂直
     * @return
     */
    public RVUtils gridManager(int spanCount, boolean isVertical) {
        rv.setLayoutManager(new StaggeredGridLayoutManager(
                spanCount,
                isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL));
        return this;
    }

    /**
     * 设置流式列表
     */
    public RVUtils staggerManager(int spanCount, boolean isVertical){
        rv.setLayoutManager(new StaggeredGridLayoutManager(spanCount,
                isVertical?StaggeredGridLayoutManager.VERTICAL:StaggeredGridLayoutManager.HORIZONTAL));
        return this;
    }

    /**
     * 设置布局管理器
     */
    public RVUtils manager(RecyclerView.LayoutManager manager) {
        if (manager == null) {
            rv.setLayoutManager(new LinearLayoutManager(context));
        } else {
            rv.setLayoutManager(manager);
        }
        return this;
    }

    /**
     * 设置为横向布局
     */
    public RVUtils managerHorizontal(){
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rv.setLayoutManager(manager);
        return this;
    }

    /**
     * 设置添加和删除动画
     * 可参考 implementation 'jp.wasabeef:recyclerview-animators:2.3.0'
     */
    public RVUtils animator(RecyclerView.ItemAnimator animator) {
        if (animator == null) {
            rv.setItemAnimator(new DefaultItemAnimator());
        } else {
            rv.setItemAnimator(animator);
        }
        return this;
    }

    /**
     * 设置是否固定大小列表项
     */
    public RVUtils fixed(boolean b) {
        rv.setHasFixedSize(b);
        return this;
    }

    /**
     * 设置适配器
     * @param list         数据源
     * @param data         绑定数据到UI上
     * @param cellView     设置返回的列表项布局索引
     * @param itemLayoutId 列表项布局
     * @param <T>
     */
    public <T> void adapter(List<T> list, final onBindData data, final SetMultiCellView cellView, int... itemLayoutId) {
        if (rv.getLayoutManager() == null) {
            rv.setLayoutManager(new LinearLayoutManager(context));
        }
        this.dataList = list;
        adapter = new EasyRVAdapter<T>(context, list, itemLayoutId) {
            @Override
            protected void onBindData(EasyRVHolder viewHolder, int position, T item) {
                data.bind(viewHolder, position);
            }

            @Override
            public int getLayoutIndex(int layoutIndex, T item) {
                return cellView.setMultiCellView(layoutIndex);
            }
        };
        rv.setAdapter(adapter);
    }

    public EasyRVAdapter getAdapter() {
        return adapter;
    }

    /**
     * 绑定数据
     */
    public interface onBindData {
        void bind(EasyRVHolder holder, int pos);
    }

    /**
     * 设置多个列表项布局
     */
    public interface SetMultiCellView {
        int setMultiCellView(int position);
    }

}

/*
val list = ArrayList<String>()
        for (i in 1..100){
            list.add("ITEM"+(i+1))
        }
        val rvUtils = RVUtils(rv)
        val heights = ArrayList<Int>()
        for (i in 1..100){
            heights.add((100 + Math.random() * 300).toInt())
        }
        rvUtils.staggerManager(4,true)
                .animator(null)
                .enableDraggableItem(list,true)
                .rvAdapter(list, { holder, pos ->
                    val tv = holder.getView<TV>(R.id.tv_cell)
                    val lp = tv.layoutParams
                    lp.height = heights[pos]
                    holder.setText(R.id.tv_cell, list[pos])
                    holder.getItemView().click {
                        rvUtils.doDelete(pos, list.size)
                    }
                    holder.getItemView().setOnLongClickListener {
                        if(pos!=0){
                            rvUtils.getmItemTouchHelper().startDrag(holder)
                        }
                        return@setOnLongClickListener true
                    }
                    tv.setBackgroundColor("#${getRandColorCode()}".color())
                },R.layout.cell)
 */