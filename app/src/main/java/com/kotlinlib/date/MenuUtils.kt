package com.kotlinlib.date

import android.support.v4.widget.DrawerLayout
import android.view.View

class MenuUtils {

    /**
     * 初始化侧滑菜单
     */
    fun initDrawer(drawerLayout: DrawerLayout, gravity:Int){
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, gravity)
        drawerLayout.setDrawerListener(object : DrawerLayout.SimpleDrawerListener(){
            override fun onDrawerClosed(drawerView: View) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, gravity)
            }
        })
    }

    /**
     * 打开侧滑菜单
     */
    fun openDrawer(drawerLayout: DrawerLayout, gravity:Int){
        drawerLayout.openDrawer(gravity)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,gravity)
    }

}