package com.androidui.supportlib

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_recycler_view.*

@LayoutId(R.layout.activity_recycler_view)
class RecyclerViewActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        tvInfo1.text = """
            RecyclerView Animators is an Android library that allows developers to easily create RecyclerView with animations.

            Please feel free to use this.

            Features
            Animate addition and removal of ItemAnimator
            Appearance animations for items in RecyclerView.Adapter
        """.trimIndent()
    }
}
