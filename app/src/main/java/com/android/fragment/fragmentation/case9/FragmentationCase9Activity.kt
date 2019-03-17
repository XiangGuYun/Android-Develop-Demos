package com.android.fragment.fragmentation.case9

import android.os.Bundle
import com.android.R
import com.android.fragment.fragmentation.case1.FragmentationCase1Fragment
import com.android.fragment.fragmentation.case2.FragmentationCase2Fragment
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.new_fragment.FragmentationPagerUtils
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_fragmentation_case9.*

@LayoutId(R.layout.activity_fragmentation_case9)
class FragmentationCase9Activity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        FragmentationPagerUtils(this, viewPager,
                listOf(FragmentationCase1Fragment.newInstance(), FragmentationCase2Fragment.newInstance()))
                .addTabLayout(tabLayout,
                        {
                            tab, index ->
                            tab.text = "Fragment${index+1}"
                        },
                        {

                        },
                        {

                        })

    }

}
