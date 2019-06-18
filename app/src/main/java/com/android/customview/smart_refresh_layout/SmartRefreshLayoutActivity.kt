package com.android.customview.smart_refresh_layout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_swipe_refresh_layout2.*

@LayoutId(R.layout.activity_swipe_refresh_layout2)
class SmartRefreshLayoutActivity : KotlinActivity() {

    val list = (1..10).toMutableList()

    override fun init(bundle: Bundle?) {

        RVUtils(rvTest)
                .decorate().rvAdapter(
                list,
                {
                    holder, pos ->
                    holder.text(R.id.tvCell, "Item${pos+1}")
                },R.layout.item_tv
        )

        srlTest.setOnLoadMoreListener {
            list.add(0, 0)
            rvTest.update()
            srlTest.finishLoadMore()
        }

        srlTest.setOnRefreshListener {
            list.add(list.size)
            rvTest.update()
            srlTest.finishRefresh()
        }

        //设置独立的HeaderView
        srlTest.setRefreshHeader(GifHeaderView(this))



    }

}
