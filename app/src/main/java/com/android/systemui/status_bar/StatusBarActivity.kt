package com.android.systemui.status_bar

import android.graphics.Color
import android.os.Bundle
import com.android.R
import com.githang.statusbar.StatusBarCompat
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.other.StatusBarColor
import kotlinx.android.synthetic.main.activity_status_bar.*

@StatusBarColor("#00000000")
@LayoutId(R.layout.activity_status_bar)
class StatusBarActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        setStatusBarTextBlack(true)

        header1.setLeftClick { codeDialog.text("""
1、在AndroidManifest.xml文件中修改theme为android:theme=”@android:style/Theme.NoTitleBar.Fullscreen”

2、在setContentView方法前执行如下代码：
requestWindowFeature(Window.FEATURE_NO_TITLE)
getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

3、通过View的setSystemUiVisibility方法

4、通过如下代码实现状态栏的隐藏和显示：
getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //隐藏状态栏
getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //显示状态栏

方法1、2只能消除状态栏，不能显示状态栏。

方法3，我采用过，调用setSystemUiVisibility方法，该方法传入的参数可以为：
View.SYSTEM_UI_FLAG_VISIBLE：显示状态栏，Activity不全屏显示(恢复到有状态的正常情况)。
View.INVISIBLE：隐藏状态栏，同时Activity会伸展全屏显示。
View.SYSTEM_UI_FLAG_FULLSCREEN：Activity全屏显示，且状态栏被隐藏覆盖掉。
View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN：Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住。
View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION：效果同View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
View.SYSTEM_UI_LAYOUT_FLAGS：效果同View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
View.SYSTEM_UI_FLAG_HIDE_NAVIGATION：隐藏虚拟按键(导航栏)。有些手机会用虚拟按键来代替物理按键。
View.SYSTEM_UI_FLAG_LOW_PROFILE：状态栏显示处于低能显示状态(low profile模式)，状态栏上一些图标显示会被隐藏。
这里我需要传入的是View.SYSTEM_UI_FLAG_FULLSCREEN，可是当我传入该参数后，结果是：只是状态栏消失了，但是位置还在。（测试手机：华为荣耀8 系统是基于Android 7.0的EMUI 5.0；三星galaxy s6 系统是Android 6.0）

最终，只有方法4可以正常使用。
        """.trimIndent()) }
                .setRightClick { codeDialog.text("""
private fun fullscreen(enable: Boolean) {

    if (enable) { //显示状态栏

        val lp = window.attributes

        lp.flags = lp.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN

        window.attributes = lp

        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    } else { //隐藏状态栏

        val lp = window.attributes

        lp.flags = lp.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()

        window.attributes = lp

        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    }

}
            """.trimIndent())
        }

        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                fullscreen(true)
            } else {
                fullscreen(false)
            }
        }

        switch2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                setStatusBarTextBlack(true)
            } else {
                setStatusBarTextBlack(false)
            }
        }

        btn1.click {
            colorPicker("状态栏颜色"){
                StatusBarCompat.setStatusBarColor(this, it)
            }
        }

    }
}
