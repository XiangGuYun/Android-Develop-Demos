package com.androidui.supportlib.drawerlayout

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.DrawerLayout.*
import android.view.Gravity
import android.view.View
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_drawer_layout_case1.*

@LayoutId(R.layout.activity_drawer_layout_case1)
class DrawerLayoutCase1Activity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        btn1.click {
            drawer.openDrawer(Gravity.LEFT)
        }
        btn2.click {
            drawer.closeDrawer(Gravity.LEFT);
        }

        var move = 0f
        drawer.setDrawerListener(object :DrawerLayout.DrawerListener{
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
