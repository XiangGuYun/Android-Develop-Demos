package com.androidui.systemui.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_et2.*

@LayoutId(R.layout.activity_et2)
class ET2Activity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        showKeyboardDelay(etTest)
    }
}
