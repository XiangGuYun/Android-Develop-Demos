package com.android.fragment.case1

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.fragment.FragmentUtils
import com.kotlinlib.other.LayoutId

@LayoutId(R.layout.activity_test)
class TestActivity : KotlinActivity() {
    lateinit var fu: FragmentUtils<TestFragment>
    override fun init(bundle: Bundle?) {
        fu = FragmentUtils(this, TestFragment(), R.id.flContainer)
    }
}
