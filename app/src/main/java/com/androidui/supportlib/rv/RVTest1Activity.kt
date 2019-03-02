package com.androidui.supportlib.rv

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.DividerGridItemDecoration
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_rvtest1.*

@LayoutId(R.layout.activity_rvtest1)
class RVTest1Activity : KotlinActivity() {

    val testData = listOf(R.mipmap.img1,R.mipmap.img1, R.mipmap.bg_girl1, R.mipmap.img3, R.mipmap.bg_girl, R.mipmap.header1,
            R.mipmap.header2,R.mipmap.header3,R.mipmap.header5,R.mipmap.header6,R.mipmap.header7,R.mipmap.img1)

    override fun init(bundle: Bundle?) {
        RVUtils(rv1)//传入RecyclerView对象
                .gridManager(2)//设置2格纵向网格布局
                .rvAdapterHF(testData, //数据源
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
                            normalHolder.ir(R.id.ivCell, testData[pos])//处理普通ItemView
                        },
                        {
                            0 //返回普通ItemView的布局下标，从0开始
                        }, R.layout.iv_grid_item)//普通ItemView布局
    }
}
