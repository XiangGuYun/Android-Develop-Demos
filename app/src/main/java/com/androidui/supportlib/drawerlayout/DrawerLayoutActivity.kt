package com.androidui.supportlib.drawerlayout

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_drawer_layout.*

@LayoutId(R.layout.activity_drawer_layout)
class DrawerLayoutActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        header1.setLeftClick {
            codeDialog.text("""
1.DrawerLayout里面只能放两个子容器，第一个是主界面，第二个是侧滑页。
2.侧滑页必须指定android:layout_gravity属性来表示其所在位置。
3.侧滑页在宽度为match_parent的情况下是无法铺满屏幕的，如果要实现，可以设置android:layout_marginRight="-65dp"（以居左为例）。
4.侧滑页根布局尽量使用约束布局，否则上边距可能会无效。
5.DrawerLayout只支持左滑和右滑。

问题：如何打开和关闭菜单？
打开菜单有四个重载方法，关闭菜单与此类似：
1.void openDrawer(int gravity)
2.void openDrawer(View drawerView, boolean animate)
3.void openDrawer(int gravity)
4.void openDrawer(View drawerView, boolean animate)
分析：一个参数的方法打开和关闭都是带有动画的，如果不想要动画可以用两个参数的方法进行关闭。
gravity是指定对齐方式进行打开关闭，drawerView是指定侧滑页对象进行打开关闭，两者效果完全一致。

问题：如何判断菜单已经打开？
isDrawerOpen

问题：如何判断菜单可见？
isDrawerVisible

问题：如何对菜单的事件进行监听？
 drawer.setDrawerListener(object :DrawerLayout.DrawerListener{
    override fun onDrawerStateChanged(newState: Int) {
        when(newState){
            STATE_IDLE->{
                tvState.text = "IDLE"//空闲状态
            }
            STATE_DRAGGING->{
                tvState.text = "DRAGGING"//拖拽状态
            }
            STATE_SETTLING->{
                tvState.text = "SETTLING"//稳定状态
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
        tvSlideOffset.text = "滑动值：$'slideOffset"
    }

    override fun onDrawerClosed(drawerView: View) {
        "菜单关闭了".toast()
    }

    override fun onDrawerOpened(drawerView: View) {
        "菜单打开了".toast()
    }
})
            """.trimIndent())
        }

        btnView1.click {go( DrawerLayoutCase1Activity::class.java )}
        btnView2.click { go( DrawerLayoutCase2Activity::class.java ) }
    }

}
