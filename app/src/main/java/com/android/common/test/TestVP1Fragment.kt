package com.android.common.test

import android.graphics.Color
import android.os.Handler
import com.android.R
import com.kotlinlib.fragment.FragPagerEngine
import com.kotlinlib.listener.OnPageChange
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import kotlinx.android.synthetic.main.activity_test_vp.*
import kotlinx.android.synthetic.main.fragment_test_vp.*

@LayoutId(R.layout.fragment_test_vp)
class TestVP1Fragment:KotlinFragment() {
    override fun init() {
        bg.setBackgroundColor(Color.BLUE)
        FragPagerEngine(this, vpInside, TestInside1Fragment(), TestInside2Fragment())
        val handler = Handler()
        refresh.setOnRefreshListener {
            handler.postDelayed({
                refresh.isRefreshing = false
            },1500)
        }

        vpInside.setOnPageChangeListener(object :OnPageChange{
            override fun onPageSelected(position: Int) {
                (getAct() as TestVPActivity).vpOutside.canScrollToNext = position != 1
            }
        })

    }
}