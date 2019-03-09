package com.android.anim

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_anim.*

@LayoutId(R.layout.activity_anim)
class AnimActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        btn1.click { go(FrameAnimActivity::class.java) }
        btn2.click { go(InterpolatorActivity::class.java) }
        btn3.click { go(PatchAnimActivity::class.java) }
        btn4.click { go(PropertyAnimActivity::class.java) }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.my_fade_in, R.anim.my_fade_out)
    }
}
