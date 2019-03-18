package com.android.systemui.scrollview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.android.R
import kotlinx.android.synthetic.main.activity_sv3.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class SV3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sv3)
        OverScrollDecoratorHelper.setUpOverScroll(scrollView)
    }
}
