package com.androidui.supportlib.rv

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_recycler_view.*

@LayoutId(R.layout.activity_recycler_view)
class RecyclerViewActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        btnViewResult0.click {  go(RVTest0Activity::class.java) }
        btnViewResult1.click {  go(RVTest1Activity::class.java) }
        btnViewResult2.click {  go(RVTest2Activity::class.java) }
        btnViewResult3.click {  go(RVTest3Activity::class.java) }
        read0.setText("""
val data = (0..31).toList().map { "Item$'it" }
RVUtils(rv1)//传入RecyclerView对象
        .rvAdapterHF(data, //数据源
                R.layout.header1, //HeaderView布局
                {
                    headerHolder ->
                    headerHolder.text(R.id.tvHeader, "Header")//处理HeaderView
                },
                R.layout.header1,
                {
                    footerHolder ->
                    footerHolder.text(R.id.tvHeader, "Footer")//处理HeaderView
                },
                {
                    normalHolder, pos ->
                    normalHolder.text(R.id.tvItem, data[pos])//处理普通ItemView
                },
                {
                    0 //返回普通ItemView的布局下标，从0开始
                }, R.layout.rv_item1)//普通ItemView布局
        """.trimIndent())
        read1.setText("""
//准备数据
val data = (0..31).toList().map { "Item$'it" }
RVUtils(rv1)//传入RecyclerView对象
        .gridManager(2)//设置2格纵向网格布局
        .rvAdapterHF(data, //数据
                R.layout.header1, //HeaderView布局
                {
                    headerHolder ->
                    headerHolder.text(R.id.tvHeader, "Header")//处理HeaderView
                },
                R.layout.header1,
                {
                    footerHolder ->
                    footerHolder.text(R.id.tvHeader, "Footer")//处理HeaderView
                },
                {
                    normalHolder, pos ->
                    normalHolder.text(R.id.tvItem, data[pos])//处理普通ItemView
                },
                {
                    0 //返回普通ItemView的布局下标，从0开始
                }, R.layout.rv_item1)//普通ItemView布局
            """.trimIndent())
    }
}
