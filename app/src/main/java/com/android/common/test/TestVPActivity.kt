package com.android.common.test

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.fragment.FragPagerEngine
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_test_vp.*

@LayoutId(R.layout.activity_test_vp)
class TestVPActivity : KotlinActivity() {



    override fun init(bundle: Bundle?) {
        FragPagerEngine(this,
                vpOutside,
                TestVP1Fragment(), TestVP2Fragment())
    }

}
