package com.android.fragment

import android.os.Bundle
import com.android.R
import com.android.fragment.case1.TestActivity
import com.android.fragment.case2.Case2Activity
import com.android.fragment.case3.Case3Activity
import com.android.fragment.fragmentation.FragmentationActivity
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_my_fragment.*

@LayoutId(R.layout.activity_my_fragment)
class MyFragmentActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        btnHelloFragment.click { go(TestActivity::class.java) }
        btnBookFragment.click { go(Case2Activity::class.java) }
        btnFourFragments.click { go(Case3Activity::class.java) }
        btnFragmentation.click { go(FragmentationActivity::class.java) }
    }
}
