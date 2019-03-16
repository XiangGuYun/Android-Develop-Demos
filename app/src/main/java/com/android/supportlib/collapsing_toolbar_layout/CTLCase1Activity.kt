package com.android.supportlib.collapsing_toolbar_layout

import android.os.Bundle
import android.view.WindowManager
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId

@LayoutId(R.layout.activity_ctlcase1)
class CTLCase1Activity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

}
