package com.androidui.customview

import android.os.Bundle
import com.androidui.R
import com.androidui.customview.drag.ViewDragHelperActivity
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_custom_view.*

@LayoutId(R.layout.activity_custom_view)
class CustomViewActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        btnViewDragHelper.click {
            go(ViewDragHelperActivity::class.java)
        }
    }
}
