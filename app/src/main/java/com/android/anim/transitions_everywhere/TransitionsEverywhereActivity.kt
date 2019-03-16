package com.android.anim.transitions_everywhere

import android.os.Bundle
import com.android.R
import com.android.anim.transitions_everywhere.fragment.*
import com.android.supportlib.coordinate.fragment.TestFragment
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.fragment.FragPagerEngine
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_transitions_everywhere.*

@LayoutId(R.layout.activity_transitions_everywhere)
class TransitionsEverywhereActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        FragPagerEngine(this, viewPager, AutoTransitionSample(), ChangeTextSample(),
                CustomTransitionSample(), ExplodeAndEpicenterExample(), ImageTransformSample(),
                InterpolatorDurationStartDelaySample())
                .addTabLayout(tabLayout,
                        {
                            tab, index ->
                            tab.text = "动画${index+1}"
                        },
                        {

                        },
                        {

                        }, true)

    }

}
