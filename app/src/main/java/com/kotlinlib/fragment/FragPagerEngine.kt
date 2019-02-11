package com.kotlinlib.fragment

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import com.kotlinlib.listener.OnTabSelected
import com.kotlinlib.view.KotlinFragment

class FragPagerEngine {
    private var fpUtils: FragPagerUtils<KotlinFragment>
    constructor(act:FragmentActivity?, vp:ViewPager, vararg frags:KotlinFragment){
        fpUtils = FragPagerUtils(act, vp, frags.asList().toMutableList())
    }
    constructor(act:Fragment?, vp:ViewPager, vararg frags:KotlinFragment){
        fpUtils = FragPagerUtils(act, vp, frags.asList().toMutableList())
    }
    constructor(act:FragmentActivity?, vp:ViewPager, frags:ArrayList<KotlinFragment>){
        fpUtils = FragPagerUtils(act, vp, frags)
    }

    constructor(act:FragmentActivity?, vp:ViewPager, frags:List<KotlinFragment>){
        fpUtils = FragPagerUtils(act, vp, frags)
    }

    /**
     * 添加新的Fragment并刷新ViewPager和TabLayout
     * @param fragment KotlinFragment
     * @param updateTab (tab:TabLayout.Tab)->Unit
     */
    fun addAndUpdate(fragment: KotlinFragment, updateTab:(tab:TabLayout.Tab)->Unit) {
        fpUtils.fragments.add(fragment)
        fpUtils.adapter.notifyDataSetChanged()
        for (i in fpUtils.fragments.indices) {
            updateTab.invoke(fpUtils.tabLayout.getTabAt(i)!!)
        }
        fpUtils.tabLayout.getTabAt(fpUtils.fragments.size-1)?.select()
    }

    fun addTabLayout(tabLayout: TabLayout,setTab:(tab:TabLayout.Tab,index:Int)->Unit,
                     onSelected:(tab:TabLayout.Tab)->Unit,onUnSelected:(tab:TabLayout.Tab)->Unit,isScroll:Boolean=false): FragPagerEngine {
        fpUtils.addTabLayout(tabLayout,true, isScroll) { tab, index ->
           setTab.invoke(tab,index)
        }
        tabLayout.addOnTabSelectedListener(object : OnTabSelected {
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                onUnSelected.invoke(tab!!)
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                onSelected.invoke(tab!!)
            }
        })
        return this
    }

    fun getUtils(): FragPagerUtils<KotlinFragment> {
        return fpUtils
    }
}