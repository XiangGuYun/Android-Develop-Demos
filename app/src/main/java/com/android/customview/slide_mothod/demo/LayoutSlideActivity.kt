package com.android.customview.slide_mothod.demo

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_layout_slide.*
import java.util.*

@LayoutId(R.layout.activity_layout_slide)
class LayoutSlideActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        //渐进滑动
        btn.click {
            view.layout(0, 0, view.width, view.height)
            Timer().scheduleAtFixedRate(object :TimerTask(){
                override fun run() {
                   runOnUiThread {
                       if(view.right<=srnWidth)
                           view.layout(view.left+1, view.top, view.right+1, view.bottom)
                   }
                }
            },100,5)
        }

        //瞬间滑动
        btn1.click {
            if(view1.left==0){
                view1.layout(srnWidth-view1.width, view1.top, srnWidth, view1.bottom)
            }else{
                view1.layout(0, view1.top, view1.width, view1.height*2)
            }
        }

    }

}
