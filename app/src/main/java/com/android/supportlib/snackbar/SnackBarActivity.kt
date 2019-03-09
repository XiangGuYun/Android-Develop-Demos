package com.android.supportlib.snackbar

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_snackbar.*

@LayoutId(R.layout.activity_snackbar)
class SnackBarActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        btn1.click { go(SnackBarCase1Activity::class.java) }
    }

}
