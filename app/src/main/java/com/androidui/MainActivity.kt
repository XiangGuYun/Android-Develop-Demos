package com.androidui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidui.huitu.HuiTuActivity
import com.androidui.supportlib.SupportLibUIActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnHuiTu.setOnClickListener {
            startActivity(Intent(this, HuiTuActivity::class.java))
        }

        btnSupportLibUI.setOnClickListener {
            startActivity(Intent(this, SupportLibUIActivity::class.java))
        }

    }
}
