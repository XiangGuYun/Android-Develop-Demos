package com.androidui.fragment.case3

import com.androidui.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import kotlinx.android.synthetic.main.fragment_case3.*

@LayoutId(R.layout.fragment_case3)
class Case3Fragment:KotlinFragment(){

    override fun init() {
        tv.text = arguments?.getString("text")
        println("fragment${arguments?.getString("text")}初始化了")
    }

}
