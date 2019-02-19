package com.androidui.anim

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId

@LayoutId(R.layout.activity_second)
class SecondActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {

    }

    override fun finish() {
        super.finish()
        when(intent.getIntExtra("type",1)){
            //上个Activity将会由浅到深淡入，当前Activity将会由深到浅淡出
            1->overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            //上个Activity将会从左边滑入，当前Activity将会向右边滑出
            2->overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            3->overridePendingTransition(R.anim.my_fade_in, R.anim.my_fade_out)
            4->overridePendingTransition(R.anim.my_up_in1, R.anim.my_down_out1)
            5->overridePendingTransition(R.anim.my_scale_in, R.anim.my_scale_out)
            6->overridePendingTransition(R.anim.my_set_in, R.anim.my_set_out)
        }

    }

}
