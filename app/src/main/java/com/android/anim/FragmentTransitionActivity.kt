package com.android.anim

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.fragment.FragTabEngine
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_fragment_transition.*

@LayoutId(R.layout.activity_fragment_transition)
class FragmentTransitionActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        FragTabEngine(this, tabLayout, R.id.fl,
            {
                i, tab ->
                tab.setText("Fragment$i")
            },
            {
                fu, frags, tab ->
                fu.switch(frags[tab.position]){
                    when(intent.getIntExtra("type", 1)){
                        1->it.setTransition(FragmentTransaction.TRANSIT_NONE)
                        2->it.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        3->it.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        4->it.setCustomAnimations(R.anim.my_fade_in, R.anim.my_fade_out)
                        5->it.setCustomAnimations(R.anim.my_up_in, R.anim.my_down_out)
                        6->it.setCustomAnimations(R.anim.my_scale_in, R.anim.my_scale_out)
                        7->it.setCustomAnimations(R.anim.my_set_in, R.anim.my_set_out)
                    }
                }
            },
            {
                tab ->
            },
            TestFragment().apply { arguments = Bundle().apply { putInt("img", R.mipmap.meinv) } },
            TestFragment().apply { arguments = Bundle().apply { putInt("img", R.mipmap.header1) } },
            TestFragment().apply { arguments = Bundle().apply { putInt("img", R.mipmap.header2) }})
    }
}
