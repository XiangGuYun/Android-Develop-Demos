package com.androidui.customview.drag

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_case1.*

@LayoutId(R.layout.activity_case1)
class Case1Activity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        dragView.tv1 = tv1
        dragView.tv2 = tv2
        dragView.tv3 = tv3
    }
}
