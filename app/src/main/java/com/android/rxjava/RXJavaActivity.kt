package com.android.rxjava

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_rxjava.*

@LayoutId(R.layout.activity_rxjava)
class RXJavaActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        btnCreateOp.click { go(CreateOperatorActivity::class.java) }

    }

}
