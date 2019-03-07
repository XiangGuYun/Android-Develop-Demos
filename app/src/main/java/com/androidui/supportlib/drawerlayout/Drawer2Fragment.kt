package com.androidui.supportlib.drawerlayout

import com.androidui.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import kotlinx.android.synthetic.main.fragment_drawer1.*

@LayoutId(R.layout.fragment_drawer1)
class Drawer2Fragment:KotlinFragment(){

    override fun init() {
        tv1.text = "第二层抽屉"
        btnEnter.text = "回退"
        btnEnter.click { (getAct() as DrawerLayoutCase2Activity).fu.switch(0){
            it.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }}
    }

}
