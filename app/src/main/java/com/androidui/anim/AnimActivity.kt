package com.androidui.anim

import android.os.Bundle
import com.androidui.R
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
}
