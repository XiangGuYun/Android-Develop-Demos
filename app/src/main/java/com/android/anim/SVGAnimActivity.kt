package com.android.anim

import android.graphics.drawable.Animatable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_svganim.*

@LayoutId(R.layout.activity_svganim)
class SVGAnimActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        btn1.click {
            (ivTest.drawable as Animatable).start()
        }
    }

}
