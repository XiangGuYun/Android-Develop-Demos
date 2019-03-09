package com.android.customview.slide_mothod.demo

import android.os.Bundle
import android.view.ViewGroup
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_scroll_to_or_by.*
import java.util.*

@LayoutId(R.layout.activity_scroll_to_or_by)
class ScrollToOrByActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        //渐进滑动ScrollBy
        var movement1 = 0
        var isRight1 = true //是否向右滑

        btn1.setOnClickListener {
            Timer().scheduleAtFixedRate(object : TimerTask(){
                override fun run() {
                    runOnUiThread {
                        if(movement1 <= srnWidth-view1.width&&isRight1){
                            (view1.parent as ViewGroup).scrollBy(-1,0)
                            movement1 += 1
                        } else {
                            isRight1 = false
                            if(movement1>=0){
                                (view1.parent as ViewGroup).scrollBy(1,0)
                                movement1 -= 1
                            }else{
                                isRight1 = true
                            }
                        }
                    }
                }
            },100,5)
        }

        //瞬间滑动ScrollBy
        var inRight1 = false//是否在右侧

        btn2.setOnClickListener {
            if(!inRight1){
                (view2.parent as ViewGroup).scrollBy(view2.width-srnWidth, 0)
                inRight1 = true
            }else{
                (view2.parent as ViewGroup).scrollBy(srnWidth-view2.width, 0)
                inRight1 = false
            }
        }

        //渐进滑动ScrollTo
        var movement4 = 0
        var isRight4 = true //是否向右滑
        btn3.setOnClickListener {
            Timer().scheduleAtFixedRate(object : TimerTask(){
                override fun run() {
                    runOnUiThread {
                        if(-movement4 <= srnWidth-view3.width&&isRight4){
                            (view3.parent as ViewGroup).scrollTo(movement4--, 0)
                        } else {
                            isRight4 = false
                            if(-movement4>=0){
                                (view3.parent as ViewGroup).scrollTo(movement4++, 0)
                            }else{
                                isRight4 = true
                            }
                        }

                    }
                }
            },100,5)
        }

        //瞬间滑动ScrollTo
        var inRight2 = false//是否在右侧

        btn4.setOnClickListener {
            if(!inRight2){
                (view4.parent as ViewGroup).scrollTo(view4.width-srnWidth, 0)
                inRight2 = true
            }else{
                (view4.parent as ViewGroup).scrollTo(0, 0)
                inRight2 = false
            }
        }

    }

}
