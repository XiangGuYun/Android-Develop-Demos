package com.android.supportlib.fab

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_fab.*

/**
 * https://blog.csdn.net/u010687392/article/details/46954213
 */
@LayoutId(R.layout.activity_fab)
class FABActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        header1.setLeftClick {
            codeDialog.text("""
android:src：FAB中显示的图标.
app:backgroundTint：正常的背景颜色 ，这里是ColorStateList类型
app:rippleColor：按下时的背景颜色
app:elevation：正常的阴影大小
app:pressedTranslationZ：按下时的阴影大小
app:layout_anchor：设置FAB的锚点，即以哪个控件为参照设置位置
app:layout_anchorGravity：FAB相对于锚点的位置
app:fabSize：FAB的大小，normal或mini（分别对应56dp和40dp）
app:borderWidth：边框大小，最好设置成0dp否则会有边框
android:clickable：一定要设置成true否则没有点击效果
""")
        }
        val settings = listOf("rippleColor","elevation+","elevation-","compatPressed\nTranslationZ+",
                "compatPressed\nTranslationZ-")
        RVUtils(rvSetting).gridManager(2).rvAdapter(settings,{
            holder, pos ->
            holder.setText(R.id.btnCell, settings[pos])
            holder.v(R.id.btnCell).click {
               when(pos){
                   0->{
                       colorPicker("设置点击时背景色"){
                           fab.rippleColor = it
                       }
                   }
                   1->{
                       fab.elevation = fab.elevation+5
                   }
                   2->{
                       if(fab.elevation>=0){
                           fab.elevation = fab.elevation-5
                       }else{
                           "阴影已经没有了".toast()
                       }
                   }
                   3->{
                       fab.compatPressedTranslationZ  = fab.compatPressedTranslationZ+5
                   }
                   4->{
                       if(fab.compatPressedTranslationZ>=0){
                           fab.compatPressedTranslationZ = fab.compatPressedTranslationZ-5
                       }else{
                           "按下时的阴影已经没有了".toast()
                       }
                   }
               }
            }
        },R.layout.item_btn_setting)
    }



}
