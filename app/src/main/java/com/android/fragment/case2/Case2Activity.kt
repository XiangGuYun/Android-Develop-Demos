package com.android.fragment.case2

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.fragment.FragmentUtils
import com.kotlinlib.other.LayoutId

@LayoutId(R.layout.activity_case2)
class Case2Activity : KotlinActivity() {
    lateinit var fu1: FragmentUtils<Case2ListFragment>
    lateinit var fu2: FragmentUtils<Case2DetailFragment>
    override fun init(bundle: Bundle?) {
        fu1 = FragmentUtils(this, Case2ListFragment(), R.id.flList)
        fu2 = FragmentUtils(this, Case2DetailFragment(), R.id.flDetail)
    }
}
