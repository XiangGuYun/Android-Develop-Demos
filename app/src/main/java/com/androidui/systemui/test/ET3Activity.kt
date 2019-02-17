package com.androidui.systemui.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_et3.*

@LayoutId(R.layout.activity_et3)
class ET3Activity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        etSelect.setSelection(3, 10)
        btnTest.click {
            etSelect1.requestFocus()
            etSelect1.setSelection(10)
        }
    }
}
