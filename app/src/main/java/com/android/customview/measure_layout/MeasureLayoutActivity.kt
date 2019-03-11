package com.android.customview.measure_layout

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_measure_layout.*

@LayoutId(R.layout.activity_measure_layout)
class MeasureLayoutActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        header1.setLeftClick {
            webDialog.url("MeasureSpec")
        }
    }
}
