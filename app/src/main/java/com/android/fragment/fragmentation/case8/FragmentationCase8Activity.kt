package com.android.fragment.fragmentation.case8

import android.os.Bundle
import com.android.R
import com.android.fragment.fragmentation.case1.FragmentationCase1Fragment
import com.android.fragment.fragmentation.case2.FragmentationCase2Fragment
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.new_fragment.FragmentationTabUtils
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_fragmentation_case8.*

@LayoutId(R.layout.activity_fragmentation_case8)
class FragmentationCase8Activity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        FragmentationTabUtils(this, tabLayout, R.id.flContainer,
                {
                    i, tab ->
                    return@FragmentationTabUtils tab.apply {text = "Fragment${i+1}" }
                },
                {
                     fu, frags, tab ->
                     fu.switch(tab.position)
                },
                {
                    tab ->
                }, FragmentationCase1Fragment.newInstance(), FragmentationCase2Fragment.newInstance()
                )

    }

}
