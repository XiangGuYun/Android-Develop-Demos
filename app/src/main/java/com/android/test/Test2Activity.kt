package com.android.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import com.android.R
import kotlinx.android.synthetic.main.activity_test3.*
import org.greenrobot.eventbus.EventBus

class Test2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test3)

        btnBack.setOnClickListener {
            EventBus.getDefault().post(Message.obtain().apply {
                what = 0x111
                obj = "Hello"
            })
            finish()
        }

    }
}
