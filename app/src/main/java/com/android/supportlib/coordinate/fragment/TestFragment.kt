package com.android.supportlib.coordinate.fragment

import com.android.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.fragment_test_rv.*

@LayoutId(R.layout.fragment_test_rv)
class TestFragment:KotlinFragment() {
    override fun init() {
        RVUtils(rvTest).rvAdapter((1..50).toList(),
                {
                    holder, pos ->
                    holder.text(R.id.tvCell, "Item${pos+1}")
                },R.layout.item_tv)
    }
}