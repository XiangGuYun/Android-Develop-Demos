package com.androidui.supportlib

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_support_lib_ui.*

@LayoutId(R.layout.activity_support_lib_ui)
class SupportLibUIActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        btnRecyclerView.click { go(RecyclerViewActivity::class.java) }
    }
}
