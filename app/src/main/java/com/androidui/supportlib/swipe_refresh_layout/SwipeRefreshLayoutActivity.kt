package com.androidui.supportlib.swipe_refresh_layout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_swipe_refresh_layout.*

@LayoutId(R.layout.activity_swipe_refresh_layout)
class SwipeRefreshLayoutActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        btnView.click {
            go(SwipeRefreshLayoutCase1Activity::class.java)
        }

        header.setLeftClick {
            codeDialog.text("""
isRefreshing()
判断当前的状态是否是刷新状态。

setColorSchemeResources(int... colorResIds)
设置下拉进度条的颜色主题，参数为可变参数，并且是资源id，可以设置多种不同的颜色，每转一圈就显示一种颜色。

setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener)
设置监听，需要重写onRefresh()方法，顶部下拉时会调用这个方法，在里面实现请求数据的逻辑，设置下拉进度条消失等等。

setProgressBackgroundColorSchemeResource(int colorRes)
设置下拉进度条的背景颜色，默认白色。

setRefreshing(boolean refreshing)
设置刷新状态，true表示正在刷新，false表示取消刷新。
                """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
val list = (1..30).toList().map { "Item$'it" }
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
                """.trimIndent())
        }

    }

}
