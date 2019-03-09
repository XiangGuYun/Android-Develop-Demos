package com.android.draw

import android.os.Bundle
import android.support.design.widget.ShadowDrawableWrapper
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_shadow.*

@LayoutId(R.layout.activity_shadow)
class ShadowActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        val wrapper = ShadowDrawableWrapper(this, resources.getDrawable(R.drawable.rect_btn_select), 20f, 10f, 20f)
        btnTestShadow.background = wrapper
    }

}
