package com.android.customview.slide_mothod

import android.os.Bundle
import com.android.R
import com.android.customview.slide_mothod.demo.*
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_view_slide_method.*

/**
 * 实现View滑动的几种方式
 * https://www.jianshu.com/p/9da4074b80e1
 */
@LayoutId(R.layout.activity_view_slide_method)
class ViewSlideMethodActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        doHeader()
        doButton()
    }

    private fun doButton() {
        btn1.click { go(LayoutSlideActivity::class.java) }
        btn3.click { go(OffsetLTRBActivity::class.java) }
        btn4.click { go(ScrollToOrByActivity::class.java) }
        btn5.click { go(AnimSlideActivity::class.java) }
        btn6.click { go(ScrollerActivity::class.java) }

    }

    private fun doHeader() {
        header1.setRightClick {
            codeDialog.text("""
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
            """.trimIndent())
        }
        header3.setRightClick {
            codeDialog.text("""
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
            """.trimIndent())
        }
        header4.setRightClick {
            codeDialog.text("""
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
            """.trimIndent())
        }
        header6.setRightClick {
            codeDialog.text("""
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
            """.trimIndent())
        }
    }
}
