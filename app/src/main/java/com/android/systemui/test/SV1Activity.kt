package com.android.systemui.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import com.android.R
import kotlinx.android.synthetic.main.activity_sv1.*

class SV1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sv1)
        btnTest.setOnClickListener {
            sv.fullScroll(ScrollView.FOCUS_DOWN)
        }
        btnTest1.setOnClickListener {
            sv.fullScroll(ScrollView.FOCUS_UP)
        }

    }
}
