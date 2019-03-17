package com.android.fragment.fragmentation.case4

import com.android.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import kotlinx.android.synthetic.main.fragment_fragmentation_case1.*
import kotlin.random.Random

@LayoutId(R.layout.fragment_fragmentation_case1)
class NewFragment:KotlinFragment() {
    override fun init() {
        tvFragment.text = "我是一个新的Fragment(Tag:${Random.nextInt(0,100)})"
    }

    companion object {
        fun newInstance(): NewFragment {
            return NewFragment()
        }
    }

}