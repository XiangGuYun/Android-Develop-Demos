package com.android.supportlib.collapsing_toolbar_layout

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_ctl.*

@LayoutId(R.layout.activity_ctl)
class CTLActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        header1.setLeftClick {
            codeDialog.text("""
CollapsingToolbarLayout是用来对Toolbar进行再次包装的ViewGroup，主要是用于实现折叠（其实就是看起来像伸缩~）的App Bar效果。它需要放在AppBarLayout布局里面，并且作为AppBarLayout的直接子View。CollapsingToolbarLayout主要包括几个功能（参照了官方网站上内容，略加自己的理解进行解释）：

(1) 折叠Title（Collapsing title）：当布局内容全部显示出来时，title是最大的，但是随着View逐步移出屏幕顶部，title变得越来越小。你可以通过调用setTitle函数来设置title。


(2)内容纱布（Content scrim）：根据滚动的位置是否到达一个阀值，来决定是否对View“盖上纱布”。可以通过setContentScrim(Drawable)来设置纱布的图片.


(3)状态栏纱布（Status bar scrim)：根据滚动位置是否到达一个阀值决定是否对状态栏“盖上纱布”，你可以通过setStatusBarScrim(Drawable)来设置纱布图片，但是只能在LOLLIPOP设备上面有作用。


(4)视差滚动子View(Parallax scrolling children):子View可以选择在当前的布局当时是否以“视差”的方式来跟随滚动。（PS:其实就是让这个View的滚动的速度比其他正常滚动的View速度稍微慢一点）。将布局参数app:layout_collapseMode设为parallax


(5)将子View位置固定(Pinned position children)：子View可以选择是否在全局空间上固定位置，这对于Toolbar来说非常有用，因为当布局在移动时，可以将Toolbar固定位置而不受移动的影响。 将app:layout_collapseMode设为pin。
            """.trimIndent())
        }

        btn1.click {
            go(CTLCase1Activity::class.java)
        }

    }

}
