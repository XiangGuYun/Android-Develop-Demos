package com.androidui.supportlib.sliding_pane_layout

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_sliding_pane_layout.*

@LayoutId(R.layout.activity_sliding_pane_layout)
class SlidingPaneLayoutActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        header1.setLeftClick {
            codeDialog.text("""
SlidingPaneLayout和DrawerLayout的区别
1、SlidingPaneLayout只能定义一个侧滑面板，而且必须位于左侧；而DrawerLayout可定义两个侧滑面板，一个位于左侧，另一个位于右侧，当然如果你只定义一个侧滑面板也是可以的。
2、SlidingPaneLayout的侧滑面板在滑动时，主页面也跟着往右滑；而DrawerLayout的侧滑面板在滑动时，主页面是不会滑动的，也就是说，侧滑面板会遮盖住主页面的部分UI；
3、SlidingPaneLayout在主页面任何位置水平向右滑动，都会拉出左侧面板；而DrawerLayout只有在主页面左右边缘水平滑动时，才能拉出侧滑面板；
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.SlidingPaneLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/tv_left"
        android:layout_width="200dp"
        android:layout_height="match_parent"
        android:text="左侧菜单"
        android:gravity="center"
        android:textSize="30sp"
        android:background="#ff6666"/>
    <TextView
        android:id="@+id/tv_right"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="这是内容"
        android:gravity="center"
        android:textSize="30sp"
        android:background="#66ff66"/>

</android.support.v4.widget.SlidingPaneLayout>
            """.trimIndent())
        }

        btn1.click {
            go(SlidingPaneLayoutCase1Activity::class.java)
        }

        header2.setRightClick {
            codeDialog.text("""
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.SlidingPaneLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/spl_root"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <fragment
        android:id="@+id/fragment_leftmenu"
        android:name="com.androidui.supportlib.sliding_pane_layout.LeftMenuFragment"
        android:layout_width="200dp"
        android:layout_height="match_parent"
        android:layout_gravity="left"/>
    <fragment
        android:id="@+id/fragment_rightcontent"
        android:name="com.androidui.supportlib.sliding_pane_layout.RightContentFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1"
        android:layout_gravity="right"/>

</android.support.v4.widget.SlidingPaneLayout>
            """.trimIndent())
        }

        btn2.click {
            go(SlidingPaneLayoutCase2Activity::class.java)
        }

    }

}
