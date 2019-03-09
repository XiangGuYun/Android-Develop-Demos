package com.android.customview.slide_mothod.demo

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_offset_ltrb.*
import java.util.*

@LayoutId(R.layout.activity_offset_ltrb)
class OffsetLTRBActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        //渐进滑动
        btn.setOnClickListener {
            view.layout(0, 0, view.width, view.height)
            Timer().scheduleAtFixedRate(object : TimerTask(){
                override fun run() {
                    runOnUiThread {
                        if(view.right<=srnWidth)
                            view.offsetLeftAndRight(1)
                    }
                }
            },100,5)
        }

        //瞬间滑动
        btn1.setOnClickListener {
            if(view1.left==0){
                view1.offsetLeftAndRight(srnWidth-view1.width)
            }else{
                view1.offsetLeftAndRight(view1.width-srnWidth)
            }
        }

    }

}
