package com.kotlinlib.new_fragment

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.widget.LinearLayout
import com.kotlinlib.listener.OnTabSelected
import com.kotlinlib.other.DensityUtils
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment
import java.lang.reflect.Field

/**
 * Fragmentation工具类+ViewPager+TabLayout
 */
class FragmentationPagerUtils<T : SupportFragment> {

    var viewPager: ViewPager
    var fragmentList: List<T>
    var adapter: FragAdapter?=null
    var ctx: SupportActivity
    lateinit var tabLayout: TabLayout

    /**
     *
     * @param act SupportActivity
     * @param vp ViewPager
     * @param fragments List<T>
     * @param useState Boolean 是否使用FragmentStatePagerAdapter
     * @constructor
     */
    constructor(act: SupportActivity, vp: ViewPager, fragments: List<T>) {
        ctx = act
        viewPager = vp
        fragmentList = fragments
        adapter = FragAdapter(act.supportFragmentManager, fragments)
        viewPager.adapter = adapter
    }


    /**
     * 添加TabLayout
     * @param tl
     * @param isScroll Tab栏是否可滑动
     * @param listener 点击Tab监听
     */
    fun addTabLayout(tl: TabLayout,
              setTab:(tab:TabLayout.Tab,index:Int)->Unit,
              onSelected:(tab:TabLayout.Tab)->Unit,
              onUnSelected:(tab:TabLayout.Tab)->Unit,
              isScroll: Boolean=false) {
        tabLayout = tl
        if (isScroll) {
            tabLayout.tabMode = TabLayout.MODE_SCROLLABLE//设置滑动Tab模式
        } else {
            tabLayout.tabMode = TabLayout.MODE_FIXED//设置固定Tab模式
        }

        for (i in fragmentList.indices) {
            val tab = tabLayout.newTab()
            tabLayout.addTab(tab)
        }
        //将TabLayout和ViewPager关联起来
        tabLayout.setupWithViewPager(viewPager, true)
        //Tab属性必须在关联ViewPager之后设置
        for (i in fragmentList.indices) {
            setTab.invoke(tabLayout.getTabAt(i)!!, i)
        }
        tabLayout.addOnTabSelectedListener(object : OnTabSelected {
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                onUnSelected.invoke(tab!!)
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                onSelected.invoke(tab!!)
            }
        })
    }

    /**
     * 设置指示条宽度
     * @param tabLayout
     * @param marginLeft
     * @param marginRight
     */
    fun setIndicatorWidth(tabLayout: TabLayout, marginLeft: Int, marginRight: Int) {
        val tabLayoutClass = tabLayout.javaClass
        var tabStrip: Field? = null
        try {
            tabStrip = tabLayoutClass.getDeclaredField("slidingTabIndicator")
            tabStrip!!.isAccessible = true
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }

        var layout: LinearLayout? = null
        try {
            if (tabStrip != null) {
                layout = tabStrip.get(tabLayout) as LinearLayout
            }
            for (i in 0 until layout!!.childCount) {
                val child = layout.getChildAt(i)
                child.setPadding(0, 0, 0, 0)
                val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)
                params.marginStart = DensityUtils.dip2px(ctx, marginLeft.toFloat())
                params.marginEnd = DensityUtils.dip2px(ctx, marginRight.toFloat())
                child.layoutParams = params
                child.invalidate()
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    inner class FragAdapter internal constructor(fm: FragmentManager, private val fragments: List<T>) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItemPosition(`object`: Any): Int {
            // TODO Auto-generated method stub
            return PagerAdapter.POSITION_NONE
        }

    }

}
