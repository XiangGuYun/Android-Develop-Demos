package com.kotlinlib.new_fragment

import android.support.design.widget.TabLayout
import com.kotlinlib.listener.OnTabSelected
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment

/**
 * Fragmentation工具类+FrameLayout+TabLayout
 */
class FragmentationTabUtils<T:SupportFragment> {

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
    constructor(act:SupportActivity,
                tabLayout: TabLayout,
                id:Int,
                setTab:(i:Int, tab:TabLayout.Tab)->TabLayout.Tab,
                onSelected:(fu:FragmentationUtils<T>,frags:ArrayList<T>, tab:TabLayout.Tab)->Unit,
                onUnSelected: (tab: TabLayout.Tab) -> Unit, vararg frags:T, showPos:Int=0){
        val fu = FragmentationUtils<T>(act, id, *frags, showPosition = showPos)

        val fragments = ArrayList(frags.asList())
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

}