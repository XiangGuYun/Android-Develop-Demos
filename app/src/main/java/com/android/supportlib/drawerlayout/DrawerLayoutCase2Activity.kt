package com.android.supportlib.drawerlayout

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.DrawerLayout.*
import android.view.Gravity
import android.view.View
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.fragment.FragmentUtils
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import kotlinx.android.synthetic.main.activity_drawer_layout_case2.*

@LayoutId(R.layout.activity_drawer_layout_case2)
class DrawerLayoutCase2Activity : KotlinActivity() {
    lateinit var fu: FragmentUtils<KotlinFragment>

    override fun init(bundle: Bundle?) {
        fu = FragmentUtils(this,  arrayListOf(Drawer1Fragment(), Drawer2Fragment()), R.id.drawerView)
        btn1.click { drawer.openDrawer(Gravity.RIGHT) }

        var move = 0f
        drawer.setDrawerListener(object : DrawerLayout.DrawerListener{
            override fun onDrawerStateChanged(newState: Int) {
                when(newState){
                    STATE_IDLE->{
                        tvState.text = "IDLE"
                    }
                    STATE_DRAGGING->{
                        tvState.text = "DRAGGING"
                    }
                    STATE_SETTLING->{
                        tvState.text = "SETTLING"
                    }
                }
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                if(slideOffset>0){
                    //打开式滑动
                    tvSlideOrientation.text = "正在打开"
                }else{
                    //关闭式滑动
                    tvSlideOrientation.text = "正在关闭"
                }
                move += slideOffset//滑动量
                tvSlideOffset.text = "滑动值：$slideOffset"
            }

            override fun onDrawerClosed(drawerView: View) {
                "菜单关闭了".toast()
            }

            override fun onDrawerOpened(drawerView: View) {
                "菜单打开了".toast()
            }
        })

    }



}
