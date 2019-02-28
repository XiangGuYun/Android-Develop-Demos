package com.androidui.supportlib

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_recycler_view.*

@LayoutId(R.layout.activity_recycler_view)
class RecyclerViewActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        val data = (0..10).toList().map { "Item$it" }
        RVUtils(rv1)//传入RecyclerView对象
                .gridManager(2)//设置2格纵向网格布局
                .rvHeaderAdapter(data, //数据源
                        R.layout.header1, //HeaderView布局
                {
                    holder ->
                    holder.text(R.id.tvHeader, "Header")//处理HeaderView
                },
                {
                    holder, pos ->
                    holder.text(R.id.tvItem, data[pos])//处理普通ItemView
                },
                {
                    1 //返回普通ItemView的布局下标，从1开始
                }, R.layout.rv_item1)//普通ItemView布局
    }
}
