package com.android.supportlib.coordinate

import android.os.Bundle
import com.android.R
import com.android.supportlib.coordinate.fragment.TestFragment
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.fragment.FragPagerEngine
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_coordinate_case2.*

@LayoutId(R.layout.activity_coordinate_case2)
class CoordinateCase2Activity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        FragPagerEngine(
                this,
                viewpager,
                TestFragment(),
                TestFragment(),
                TestFragment()
                        ).addTabLayout(tabs, {
            tab, index ->
            tab.text = "Tab${index+1}"
        },{

        },{

        })
    }

}
