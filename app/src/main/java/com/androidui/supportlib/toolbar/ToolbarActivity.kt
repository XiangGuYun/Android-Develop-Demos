package com.androidui.supportlib.toolbar

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.androidui.R
import com.androidui.dialog.SeekBartDialog
import com.androidui.dialog.TextInputDialog
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_toolbar.*
import kotlinx.android.synthetic.main.dialog_input.*
import kotlinx.android.synthetic.main.dialog_seekbar4.*
import android.view.Gravity
import android.widget.TextView
import android.support.v4.content.ContextCompat

/**
 * https://blog.csdn.net/da_caoyuan/article/details/79557704
 * @property currentColor Int
 */
@LayoutId(R.layout.activity_toolbar)
class ToolbarActivity : KotlinActivity() {
    private var currentColor = Color.parseColor("#66ffff")

    override fun init(bundle: Bundle?) {

        val inputDialog = TextInputDialog(this)

        toolbar.title = "标题"
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationIcon(R.mipmap.back)

        //点击左边返回按钮监听事件
        toolbar.setNavigationOnClickListener {
            "点击了返回按钮".toast()
        }

        btnBgColor.click {
            ColorPickerDialogBuilder
                    .with(this)
                    .setTitle("设置CardView颜色")
                    .initialColor(currentColor)
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setOnColorSelectedListener {
                        toolbar.setBackgroundColor(it)
                        currentColor = it
                    }
                    .setPositiveButton("ok") { p0, p1, p2 ->

                    }
                    .setNegativeButton("cancel") { dialog, which -> }
                    .build()
                    .show()
        }

        btnTitle.click {
            inputDialog.show()
            inputDialog.tvYes.click {
                if(inputDialog.etMain.textString.isEmpty()) return@click
                toolbar.title = inputDialog.etMain.textString
                inputDialog.dismiss()
            }
        }

        btnTitleColor.click {
            ColorPickerDialogBuilder
                    .with(this)
                    .setTitle("设置Title颜色")
                    .initialColor(currentColor)
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setOnColorSelectedListener {
                        toolbar.setTitleTextColor(it)
                    }
                    .setPositiveButton("ok") { p0, p1, p2 ->

                    }
                    .setNegativeButton("cancel") { dialog, which -> }
                    .build()
                    .show()
        }

        val sbDialog = SeekBartDialog(this)
        sbDialog.tv1.text = "上边距"
        sbDialog.tv2.text = "左边距"
        sbDialog.tv3.text = "下边距"
        sbDialog.tv4.text = "右边距"
        sbDialog.sb1.change { seekBar, progress, fromUser ->
            toolbar.titleMarginTop = progress
        }
        sbDialog.sb2.change { seekBar, progress, fromUser ->
            toolbar.titleMarginStart = progress*5
        }
        sbDialog.sb3.change { seekBar, progress, fromUser ->
            toolbar.titleMarginBottom = progress
        }
        sbDialog.sb4.change { seekBar, progress, fromUser ->
            toolbar.titleMarginEnd = progress*5
        }
        btnTitleMargin.click {
            sbDialog.show()
        }

        btnSubTitle.click {
            inputDialog.show()
            inputDialog.tvYes.click {
                if(inputDialog.etMain.textString.isEmpty()) return@click
                toolbar.subtitle = inputDialog.etMain.textString
                inputDialog.dismiss()
            }
        }

        btnSubTitleColor.click {
            ColorPickerDialogBuilder
                    .with(this)
                    .setTitle("设置SubTitle颜色")
                    .initialColor(currentColor)
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setOnColorSelectedListener {
                        toolbar.setSubtitleTextColor(it)
                    }
                    .setPositiveButton("ok") { p0, p1, p2 ->

                    }
                    .setNegativeButton("cancel") { dialog, which -> }
                    .build()
                    .show()
        }

        tvQuestion1.text = "通过继承系统style来设置标题字体大小（查看）"
        tvQuestion1.setSimpleClickText {
            codeDialog.text("""
<!--主标题-->
<style name="ToolbarTitle" parent="@style/TextAppearance.Widget.AppCompat.Toolbar.Title">
    <item name="android:textSize">30sp</item>
</style>

<!--子标题-->
<style name="ToolbarSubtitle" parent="@style/TextAppearance.Widget.AppCompat.Toolbar.Subtitle">
    <item name="android:textSize">9sp</item>
</style>

XML中
app:titleTextAppearance="@style/ToolbarTitle"
app:subtitleTextAppearance="@style/ToolbarSubtitle"

代码中
toolbar.titleTextAppearance = R.style.ToolbarTitle
toolbar.subtitleTextAppearance = R.style.ToolbarSubtitle
            """.trimIndent())
        }
        tvQuestion2.text = "通过获取标题TextView来设置标题字体大小（查看）"
        tvQuestion2.setSimpleClickText {
            codeDialog.text("""
fun getTitleViews(toolbar: Toolbar): ArrayList<TextView> {
    val list = ArrayList<TextView>()
    for (i in 0 until toolbar.childCount) {
        val view = toolbar.getChildAt(i)
        if (view is TextView) {
            list.add(view)
        }
    }
    return list
}

val titles = getTitleViews(toolbar)
titles[0].text("你好呀").size(12)
            """.trimIndent())
        }

        setTitleCenter(toolbar)

        val main = getTitleViews(toolbar)[0]

        sbMainTitleSize.max = 40
        sbMainTitleSize.progress = 20
        sbMainTitleSize.change { seekBar, progress, fromUser ->
            main.textSize = progress.toFloat()
        }
    }

    fun getTitleViews(toolbar: Toolbar): ArrayList<TextView> {
        val list = ArrayList<TextView>()
        for (i in 0 until toolbar.childCount) {
            val view = toolbar.getChildAt(i)
            if (view is TextView) {
                list.add(view)
            }
        }
        return list
    }

    fun setTitleCenter(toolbar: Toolbar) {
        val title = "title"
        val originalTitle = toolbar.title
        toolbar.title = title
        for (i in 0 until toolbar.childCount) {
            val view = toolbar.getChildAt(i)
            if (view is TextView) {
                if (title == view.text) {
                    view.gravity = Gravity.CENTER
                    val params = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT)
                    params.gravity = Gravity.CENTER
                    view.layoutParams = params
                }
            }
            toolbar.title = originalTitle
        }
    }

    private fun setTitleCenter1(toolbar: Toolbar) {
        val titleText = TextView(this)
        titleText.setTextColor(ContextCompat.getColor(this, Color.BLACK))
        titleText.setText("")
        titleText.textSize = 18f
        titleText.gravity = Gravity.CENTER
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        titleText.layoutParams = layoutParams
        toolbar.addView(titleText)
    }

}
