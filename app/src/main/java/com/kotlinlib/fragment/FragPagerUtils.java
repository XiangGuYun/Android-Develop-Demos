package com.kotlinlib.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.kotlinlib.other.DensityUtils;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by asus on 2018/1/13.
 fragPagerUtils(vp, arrayListOf(MainFragment(),MainFragment(),MainFragment()))
 .addTabLayout(tabLayout,true) { tab, index ->
 tab.text = "Fragment${index+1}"
 }
 */

public class FragPagerUtils<T extends Fragment>{

    private ViewPager viewPager;
    public List<T> fragments;
    private FragAdapter adapter;
    private Context ctx;
    public TabLayout tabLayout;

    public FragPagerUtils(FragmentActivity act, ViewPager viewPager, List<T> fragments) {
        ctx = act;
        this.viewPager = viewPager;
        this.viewPager.setOffscreenPageLimit(10);
        this.fragments = fragments;
        adapter = new FragAdapter(act.getSupportFragmentManager(),fragments);
        this.viewPager.setAdapter(adapter);
    }

    public FragPagerUtils(Fragment act, ViewPager viewPager, List<T> fragments) {
        ctx = act.getActivity();
        this.viewPager = viewPager;
        this.viewPager.setOffscreenPageLimit(10);
        this.fragments = fragments;
        adapter = new FragAdapter(act.getChildFragmentManager(),fragments);
        this.viewPager.setAdapter(adapter);
    }

    /**
     * 添加TabLayout
     * @param tl
     * @param hasVP 是否有ViewPager
     * @param isScroll Tab栏是否可滑动
     * @param listener 点击Tab监听
     */
    public void addTabLayout(TabLayout tl, boolean hasVP, boolean isScroll, TabListener listener){
        tabLayout = tl;
        if(isScroll){
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置滑动Tab模式
        }else {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置固定Tab模式
        }

        for (int i = 0; i < fragments.size(); i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tabLayout.addTab(tab);
        }
        if(hasVP){
            //将TabLayout和ViewPager关联起来
            tabLayout.setupWithViewPager(viewPager, true);
        }
        //Tab属性必须在关联ViewPager之后设置
        for (int i = 0; i < fragments.size(); i++) {
            listener.setTabContent(tabLayout.getTabAt(i), i);
        }
    }

    public void addTabLayout(TabLayout tabLayout, boolean hasVP, TabListener listener){
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置固定Tab模式
        for (int i = 0; i < fragments.size(); i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setTag(i);
            tabLayout.addTab(tab);
        }
        if(hasVP){
            //将TabLayout和ViewPager关联起来
            tabLayout.setupWithViewPager(viewPager, true);
        }
        //Tab属性必须在关联ViewPager之后设置
        for (int i = 0; i < fragments.size(); i++) {
            listener.setTabContent(tabLayout.getTabAt(i), i);
        }
    }

    public void setScroll(boolean isScroll){
        if(isScroll){
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置滑动Tab模式
        }else {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置固定Tab模式
        }
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("slidingTabIndicator");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }


    }

    /**
     * 设置指示条宽度
     * @param tabLayout
     * @param marginLeft
     * @param marginRight
     */
    public void setUpIndicatorWidth(TabLayout tabLayout, int marginLeft, int marginRight) {
        Class<?> tabLayoutClass = tabLayout.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayoutClass.getDeclaredField("slidingTabIndicator");
            tabStrip.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        LinearLayout layout = null;
        try {
            if (tabStrip != null) {
                layout = (LinearLayout) tabStrip.get(tabLayout);
            }
            for (int i = 0; i < layout.getChildCount(); i++) {
                View child = layout.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.setMarginStart(DensityUtils.Companion.dip2px(ctx,marginLeft));
                params.setMarginEnd(DensityUtils.Companion.dip2px(ctx, marginRight));
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressLint("NewApi")
    public static void setIndicatorWid(Context ctx, TabLayout tabLayout, int marginLeft, int marginRight) {
        Class<?> tabLayoutClass = tabLayout.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayoutClass.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        LinearLayout layout = null;
        try {
            if (tabStrip != null) {
                layout = (LinearLayout) tabStrip.get(tabLayout);
            }
            for (int i = 0; i < layout.getChildCount(); i++) {
                View child = layout.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.setMarginStart(DensityUtils.Companion.dip2px(ctx,marginLeft));
                params.setMarginEnd(DensityUtils.Companion.dip2px(ctx, marginRight));
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public interface TabListener{
        void setTabContent(TabLayout.Tab tab, int index);
    }

    public interface PagerSelectListener{
        void select(int pos);
    }


    public ViewPager getViewPager() {
        return viewPager;
    }

    public List<T> getFragments() {
        return fragments;
    }

    public FragAdapter getAdapter() {
        return adapter;
    }

    public class FragStateAdapter extends FragmentStatePagerAdapter {

        private List<T> fragments;

        public FragStateAdapter(FragmentManager fm, List<T> fragments) {
            super(fm);
            this.fragments=fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return PagerAdapter.POSITION_NONE;
        }

    }

    public class FragAdapter extends FragmentPagerAdapter {

        private List<T> fragments;

        FragAdapter(FragmentManager fm, List<T> fragments) {
            super(fm);
            this.fragments=fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return PagerAdapter.POSITION_NONE;
        }

    }
}
