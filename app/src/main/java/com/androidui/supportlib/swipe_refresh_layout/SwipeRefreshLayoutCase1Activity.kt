package com.androidui.supportlib.swipe_refresh_layout

import android.os.Bundle
import android.os.Handler
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_swipe_refresh_layout_case1.*
import java.util.*


@LayoutId(R.layout.activity_swipe_refresh_layout_case1)
class SwipeRefreshLayoutCase1Activity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        val list = (1..30).toList().map { "Item$it" }
        val rvUtils = RVUtils(rvRefresh)
                .rvAdapter(list,{
                    holder, pos ->
                    holder.text(R.id.tvCell, list[pos])
                },R.layout.item_tv)

        // 设置颜色属性的时候一定要注意是引用了资源文件还是直接设置16进制的颜色，因为都是int值容易搞混
        // 设置下拉进度的背景颜色，默认就是白色的
        refresh.setProgressBackgroundColorSchemeResource(android.R.color.holo_red_light)
        // 设置下拉进度的主题颜色
        refresh.setColorSchemeResources(R.color.my_red, R.color.my_green, R.color.my_blue)

        //设置监听事件
        refresh.setOnRefreshListener {
            val random = Random()
            Handler().postDelayed({
                rvUtils.dataList.add(0, "我是天才" + random.nextInt(100) + "号")
                rvRefresh.update()
                "刷新了一条数据".toast()
                // 加载完数据设置为不刷新状态，将下拉进度收起来
                refresh.isRefreshing = false
            }, 1200)

        }

    }

}
