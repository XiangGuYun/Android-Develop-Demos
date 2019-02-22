package com.androidui.test

import android.graphics.Color
import com.androidui.R
import com.kotlinlib.fragment.FragPagerEngine
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import kotlinx.android.synthetic.main.fragment_test_vp.*

@LayoutId(R.layout.fragment_test_vp1)
class TestVP2Fragment:KotlinFragment() {
    override fun init() {
//        bg.setBackgroundColor(Color.RED)
//        FragPagerEngine(this, vpInside, TestInside1Fragment(), TestInside2Fragment())
    }
}