package com.androidui.supportlib.sliding_pane_layout

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId

@LayoutId(R.layout.activity_sliding_pane_layout_case2)
class SlidingPaneLayoutCase2Activity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        val leftFragment = supportFragmentManager.findFragmentById(R.id.fragment_leftmenu)
        val rightFragment = supportFragmentManager.findFragmentById(R.id.fragment_rightcontent)
    }
}
