package com.android.common

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.ShadowDrawableWrapper
import com.android.R
import com.android.anim.AnimActivity
import com.android.customview.CustomViewActivity
import com.android.fragment.MyFragmentActivity
import com.android.gesture.GestureActivity
import com.android.draw.HuiTuActivity
import com.android.kotlin.KotlinStudyActivity
import com.android.rxjava.RXJavaActivity
import com.android.supportlib.SupportLibUIActivity
import com.android.systemui.SystemUIActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 颜色对照表
 * https://www.sioe.cn/yingyong/yanse-rgb-16/
 */
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

        btnKotlin.setOnClickListener {
            startActivity(Intent(this, KotlinStudyActivity::class.java))
            overridePendingTransition(R.anim.my_fade_in, R.anim.my_fade_out)
        }

        btnCustomUI.setOnClickListener {
            startActivity(Intent(this, CustomViewActivity::class.java))
            overridePendingTransition(R.anim.my_fade_in, R.anim.my_fade_out)
        }

        btnFragment.setOnClickListener {
            startActivity(Intent(this, MyFragmentActivity::class.java))
            overridePendingTransition(R.anim.my_fade_in, R.anim.my_fade_out)
        }

        btnRxJava.setOnClickListener {
            startActivity(Intent(this, RXJavaActivity::class.java))
        }

    }
}
