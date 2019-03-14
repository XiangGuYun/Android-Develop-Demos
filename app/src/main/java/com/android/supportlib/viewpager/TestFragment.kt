package com.android.supportlib.viewpager

import android.graphics.Color
import com.android.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import kotlinx.android.synthetic.main.fragment_test.*

@LayoutId(R.layout.fragment_test)
class TestFragment:KotlinFragment() {

    companion object {
        val KEY_TEST_FRAGMENT = "KEY_TEST_FRAGMENT"
        val KEY_TEST_FRAGMENT_COLOR = "KEY_TEST_FRAGMENT_COLOR"
    }

    override fun init() {
        tvCell.text = arguments?.getString(KEY_TEST_FRAGMENT)
        arguments?.getInt(KEY_TEST_FRAGMENT_COLOR, Color.WHITE)?.let { tvCell.setBackgroundColor(it) }
    }
}