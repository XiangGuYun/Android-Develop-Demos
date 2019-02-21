package com.androidui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_intro.*

@LayoutId(R.layout.activity_intro)
class IntroActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        ivBackground.setImageResource(R.mipmap.intro_bg1)
        Thread{
            Thread.sleep(1500)
            runOnUiThread {
                startActivity(Intent(this, MainActivity::class.java))
                overridePendingTransition(R.anim.my_fade_in, R.anim.my_fade_out)
                finish()
            }
        }.start()
    }
}
