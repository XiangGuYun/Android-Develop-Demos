package com.android.kotlin

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_kotlin_study.*

@LayoutId(R.layout.activity_kotlin_study)
class KotlinStudyActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        btnReflect.click {
            go(ReflectActivity::class.java)
        }
    }
}
