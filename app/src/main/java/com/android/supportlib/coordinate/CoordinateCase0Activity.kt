package com.android.supportlib.coordinate

import android.os.Bundle
import com.android.R
import com.android.supportlib.coordinate.case0.CoordinateCase0_1Activity
import com.android.supportlib.coordinate.case0.CoordinateCase0_2Activity
import com.android.supportlib.coordinate.case0.CoordinateCase0_3Activity
import com.android.supportlib.coordinate.case0.CoordinateCase0_4Activity
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_coordinate_case0.*

@LayoutId(R.layout.activity_coordinate_case0)
class CoordinateCase0Activity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        readView.setText("""
要让scroll_flags属性完全生效，务必遵循以下写作规范
①：根布局选择CoordinatorLayout。
②：标题栏采用AppBarLayout包裹Toolbar的方式。
③：内容如果要使用ScrollView，应该使用NestedScrollView。
④：内容根布局要加上app:layout_behavior="@string/appbar_scrolling_view_behavior"这条属性。

在没有任何scroll_flags的情况下，标题栏默认是悬浮的。
        """.trimIndent())

        header1.setRightClick {
            codeDialog.text("""
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:android="http://schemas.android.com/apk/res/android">

    <android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            app:title="学习scroll属性"
            android:layout_width="match_parent"
            android:layout_height="?android:attr/actionBarSize"
            android:background="#66ff66"
            app:layout_scrollFlags="scroll" />

    </android.support.design.widget.AppBarLayout>


    <android.support.v4.widget.NestedScrollView
        app:layout_behavior="@string/appbar_scrolling_view_behavior"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <TextView
            android:textSize="20sp"
            android:layout_margin="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@string/my_txt"/>
    </android.support.v4.widget.NestedScrollView>
</android.support.design.widget.CoordinatorLayout>
            """.trimIndent())
        }

        btn1.click {
            go(CoordinateCase0_1Activity::class.java)
        }

        header2.setLeftClick {
            codeDialog.text("""
enterAlways:值设为enterAlways的View,当ScrollView往下滚动时，该View会直接往下滚动。而不用考虑ScrollView是否在滚动。
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
<android.support.design.widget.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        app:title="学习enterAlways属性"
        android:layout_width="match_parent"
        android:layout_height="?android:attr/actionBarSize"
        android:background="#66ff66"
        app:layout_scrollFlags="scroll|enterAlways" />

</android.support.design.widget.AppBarLayout>
            """.trimIndent())
        }

        header3.setLeftClick {
            codeDialog.text("""
exitUntilCollapsed：值设为exitUntilCollapsed的View，当这个View要往上逐渐“消逝”时，会一直往上滑动，直到剩下的的高度达到它的最小高度后，再响应ScrollView的内部滑动事件。

怎么理解呢？简单解释：在ScrollView往上滑动时，首先是View把滑动事件“夺走”，由View去执行滑动，直到滑动最小高度后，把这个滑动事件“还”回去，让ScrollView内部去上滑。
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
<android.support.design.widget.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        app:title="学习exitUntilCollapsed属性"
        android:layout_width="match_parent"
        android:minHeight="?android:attr/actionBarSize"
        android:layout_height="200dp"
        android:background="#66ff66"
        app:layout_scrollFlags="scroll|exitUntilCollapsed" />

</android.support.design.widget.AppBarLayout>
            """.trimIndent())
        }

        header4.setLeftClick {
            codeDialog.text("""
enterAlwaysCollapsed：是enterAlways的附加选项，一般跟enterAlways一起使用，它是指，View在往下“出现”的时候，首先是enterAlways效果，当View的高度达到最小高度时，View就暂时不去往下滚动，直到ScrollView滑动到顶部不再滑动时，View再继续往下滑动，直到滑到View的顶部结束。
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
<android.support.design.widget.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        app:title="学习enterAlwaysCollapsed属性"
        android:layout_width="match_parent"
        android:minHeight="?android:attr/actionBarSize"
        android:layout_height="200dp"
        app:titleMarginTop="100dp"
        android:background="#66ff66"
        app:layout_scrollFlags="scroll|enterAlways|enterAlwaysCollapsed" />

</android.support.design.widget.AppBarLayout>
            """.trimIndent())
        }

        btn2.click {
            go(CoordinateCase0_2Activity::class.java)
        }

        btn3.click {
            go(CoordinateCase0_3Activity::class.java)
        }

        btn4.click {
            go(CoordinateCase0_4Activity::class.java)
        }

    }

}
