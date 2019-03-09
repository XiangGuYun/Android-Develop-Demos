package com.android.systemui.test

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_et2.*

@LayoutId(R.layout.activity_et2)
class ET2Activity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        showKeyboardDelay(etTest)
    }
}
