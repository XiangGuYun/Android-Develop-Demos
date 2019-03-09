package com.android.customview.slide_mothod.demo

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_anim_slide.*

@LayoutId(R.layout.activity_anim_slide)
class AnimSlideActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        //渐进滑动
        var inRight = false
        btn.click {
            if(!inRight){
                view.animate().translationX((srnWidth-view.width).toFloat()).setDuration(3000).start()
                inRight = true
            }else{
                view.animate().translationX(0f).setDuration(3000).start()
                inRight = false
            }
        }

        //瞬间滑动
        var inRight1 = false
        btn1.click {
            if(!inRight1){
                view1.animate().translationX((srnWidth-view1.width).toFloat()).setDuration(1).start()
                inRight1 = true
            }else{
                view1.animate().translationX(0f).setDuration(1).start()
                inRight1 = false
            }
        }

    }

}
