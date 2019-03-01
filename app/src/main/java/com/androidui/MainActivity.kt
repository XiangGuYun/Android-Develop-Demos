package com.androidui

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidui.anim.AnimActivity
import com.androidui.gesture.GestureActivity
import com.androidui.huitu.HuiTuActivity
import com.androidui.supportlib.SupportLibUIActivity
import com.androidui.systemui.SystemUIActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCatalog.typeface = Typeface.createFromAsset(assets, "font/myfont.ttf")

        btnHuiTu.setOnClickListener {
            startActivity(Intent(this, HuiTuActivity::class.java))
            overridePendingTransition(R.anim.my_fade_in, R.anim.my_fade_out)
        }

        btnSystemUI.setOnClickListener {
            startActivity(Intent(this, SystemUIActivity::class.java))
            overridePendingTransition(R.anim.my_fade_in, R.anim.my_fade_out)
        }

        btnSupportLibUI.setOnClickListener {
            startActivity(Intent(this, SupportLibUIActivity::class.java))
            overridePendingTransition(R.anim.my_fade_in, R.anim.my_fade_out)
        }

        btnAnim.setOnClickListener {
            startActivity(Intent(this, AnimActivity::class.java))
            overridePendingTransition(R.anim.my_fade_in, R.anim.my_fade_out)
        }

        btnGesture.setOnClickListener {
            startActivity(Intent(this, GestureActivity::class.java))
            overridePendingTransition(R.anim.my_fade_in, R.anim.my_fade_out)
        }

    }
}
