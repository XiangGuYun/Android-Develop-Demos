package com.androidui.supportlib.drawerlayout

import com.androidui.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import kotlinx.android.synthetic.main.fragment_drawer1.*

@LayoutId(R.layout.fragment_drawer1)
class Drawer1Fragment:KotlinFragment(){

    override fun init() {
        btnEnter.click { (getAct() as DrawerLayoutCase2Activity).fu.switch(1){
            it.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
        }}
    }

}
