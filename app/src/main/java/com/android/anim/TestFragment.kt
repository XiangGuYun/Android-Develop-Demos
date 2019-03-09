package com.android.anim

import com.android.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import kotlinx.android.synthetic.main.fragment_test_anim.*

@LayoutId(R.layout.fragment_test_anim)
class TestFragment:KotlinFragment() {
    override fun init() {
        ivTest.setImageResource(arguments?.getInt("img")!!)
    }

}