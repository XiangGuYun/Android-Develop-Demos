package com.androidui.supportlib.fab

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_fab.*

/**
 * https://blog.csdn.net/u010687392/article/details/46954213
 */
@LayoutId(R.layout.activity_fab)
class FABActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        header1.setLeftClick {
            codeDialog.text("""
app:backgroundTint - 设置FAB的背景颜色。
app:rippleColor - 设置FAB点击时的背景颜色。
app:borderWidth - 该属性尤为重要，如果不设置0dp，那么在4.1的sdk上FAB会显示为正方形，而且在5.0以后的sdk没有阴影效果。所以设置为borderWidth="0dp"。
app:elevation - 默认状态下FAB的阴影大小。
app:pressedTranslationZ - 点击时候FAB的阴影大小。
app:fabSize - 设置FAB的大小，该属性有两个值，分别为normal和mini，对应的FAB大小分别为56dp和40dp。
src - 设置FAB的图标，Google建议符合Design设计的该图标大小为24dp。
app:layout_anchor - 设置FAB的锚点，即以哪个控件为参照点设置位置。
app:layout_anchorGravity - 设置FAB相对锚点的位置，值有 bottom、center、right、left、top等。""")
        }
    }

}
