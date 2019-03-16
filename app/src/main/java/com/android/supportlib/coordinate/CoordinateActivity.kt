package com.android.supportlib.coordinate

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_coordinate.*

@LayoutId(R.layout.activity_coordinate)
class CoordinateActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        case0()

        case1()

        case2()

        case3()

    }

    private fun case0() {
        header.setRightClick {

        }

        btn.click {
            go(CoordinateCase0Activity::class.java)
        }

    }

    private fun case3() {
        header3.setLeftClick {
            webDialog.url("view/CollapsingToolbarLayout")
        }.setRightClick {
            codeDialog.text("""
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@android:color/background_light"
    android:fitsSystemWindows="true">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/main.appbar"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:fitsSystemWindows="true"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

        <android.support.design.widget.CollapsingToolbarLayout
            android:id="@+id/main.collapsing"
            android:layout_width="match_parent"
            android:layout_height="250dp"
            android:fitsSystemWindows="true"
            app:contentScrim="#ff6666"
            app:expandedTitleMarginEnd="64dp"
            app:expandedTitleMarginStart="48dp"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">

            <ImageView
                android:id="@+id/main.backdrop"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:fitsSystemWindows="true"
                android:scaleType="centerCrop"
                android:src="@mipmap/header"
                app:layout_collapseMode="parallax" />

            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                app:layout_collapseMode="pin"
                app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
                app:title="ToolBar" />
        </android.support.design.widget.CollapsingToolbarLayout>

        <android.support.design.widget.TabLayout
            android:id="@+id/tabs"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:background="?attr/colorPrimary"
            app:tabIndicatorColor="@color/colorAccent"
            app:tabIndicatorHeight="4dp"
            app:tabTextColor="#000000" />

    </android.support.design.widget.AppBarLayout>

    <android.support.v4.view.ViewPager
        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior" />

    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="end|bottom"
        android:layout_margin="15dp" />

</android.support.design.widget.CoordinatorLayout>
            """.trimIndent())
        }
        btn3.click {
            go(CoordinateCase3Activity::class.java)
        }
    }

    private fun case2() {
        header2.setLeftClick {

        }.setRightClick {
            codeDialog.text("""
<android.support.design.widget.CoordinatorLayout
    android:id="@+id/main_content"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="250dp">


        <ImageView android:layout_width="match_parent"
            android:layout_height="200dp"
            android:background="?attr/colorPrimary"
            android:scaleType="fitXY"
            android:src="@mipmap/header"
            app:layout_scrollFlags="scroll|enterAlways"/>

        <android.support.design.widget.TabLayout
            android:id="@+id/tabs"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:background="?attr/colorPrimary"
            app:tabIndicatorColor="@color/colorAccent"
            app:tabIndicatorHeight="4dp"/>

    </android.support.design.widget.AppBarLayout>


    <android.support.v4.view.ViewPager

        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"/>

    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="end|bottom"
        android:layout_margin="15dp"
        />

</android.support.design.widget.CoordinatorLayout>
            """.trimIndent())
        }

        btn2.click {
            go(CoordinateCase2Activity::class.java)
        }

    }

    private fun case1() {
        header1.setLeftClick {
            codeDialog.text("""
先来学习一下AppBarLayout
①：它是继承于LinearLayout的，默认的方向是Vertical。
②：AppBarLayout必须作为CoordinatorLayout的直接子View，否则它的大部分功能将不会生效，如layout_scrollFlags等。
③：AppBarLayout的scrollFlags属性
SCROLL_FLAG_ENTER_ALWAYS：下拉的时候，这个View也会跟着滑出。
SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED：另一种enterAlways，但是只显示折叠后的高度。
SCROLL_FLAG_EXIT_UNTIL_COLLAPSED：上拉的时候，这个View会跟着滑动直到折叠。
SCROLL_FLAG_SCROLL：这个View将会响应Scroll事件
SCROLL_FLAG_SNAP：在Scroll滑动事件结束以前 ，如果这个View部分可见，那么这个View会停在最接近当前View的位置
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
<android.support.design.widget.CoordinatorLayout
    android:id="@+id/main_content"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/appbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="#6666ff"
            app:title="ToolBar"
            app:layout_scrollFlags="scroll|enterAlways"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/>


    </android.support.design.widget.AppBarLayout>

    <android.support.v7.widget.RecyclerView
        android:id="@+id/rv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"/>

    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="end|bottom"
        android:layout_margin="15dp"
        />

</android.support.design.widget.CoordinatorLayout>
            """.trimIndent())
        }

        btn1.click {
            go(CoordinateCase1Activity::class.java)
        }
    }


}
