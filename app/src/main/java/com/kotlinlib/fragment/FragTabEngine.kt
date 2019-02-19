package com.kotlinlib.fragment

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

import com.kotlinlib.listener.OnTabSelected
import com.kotlinlib.view.KotlinFragment

class FragTabEngine {


    private var fu: FragmentUtils<Fragment>

    private val fragments: ArrayList<Fragment>

    /**
     * 快速开发Fragment和TabLayout结合的框架
     * @param act FragmentActivity
     * @param tabLayout TabLayout
     * @param setTab 设置Tab并返回出去
     * @param onSelected 当选中某个Tab时触发
     * @param onUnSelected 当为选中某个Tab时触发
     * @param frags N个Fragment
     * @constructor
     */
    constructor(act:FragmentActivity,
                tabLayout:TabLayout,
                id:Int,
                setTab:(i:Int, tab:TabLayout.Tab)->TabLayout.Tab,
                onSelected:(fu:FragmentUtils<Fragment>,frags:ArrayList<Fragment>, tab:TabLayout.Tab)->Unit,
                onUnSelected: (tab: TabLayout.Tab) -> Unit,vararg frags:Fragment){
        fragments = ArrayList(frags.asList())
        fu = FragmentUtils(act, fragments, id)
        //创建N个自定义Tab
        for (i in 0 until fragments.size){
            val tab = tabLayout.newTab()//新建
            tabLayout.addTab(setTab.invoke(i, tab))//添加
        }
        tabLayout.addOnTabSelectedListener(object : OnTabSelected {
            //当Tab选中时
            override fun onTabSelected(tab: TabLayout.Tab?) {
               onSelected.invoke(fu, fragments,tab!!)
            }
            //当Tab未选中时
            override fun onTabUnselected(tab: TabLayout.Tab?) {
               onUnSelected.invoke(tab!!)
            }
        })
    }

    fun getFragUtils(): FragmentUtils<Fragment> {
        return fu
    }

    fun getFragments(): ArrayList<Fragment> {
        return fragments
    }

}

/*
Tip1:How to switch Tab with code?
//engine.getFragUtils().switch(engine.getFragments()[2]) Don't write this code Otherwise will crash
tlHome.getTabAt(2)?.select()
 */