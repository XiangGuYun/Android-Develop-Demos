package com.androidui.supportlib.cardview

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import com.androidui.R
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.OnColorSelectedListener
import com.flask.colorpicker.builder.ColorPickerClickListener
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_card_view.*

@LayoutId(R.layout.activity_card_view)
class CardViewActivity : KotlinActivity() {

    var  currentColor:Int = Color.WHITE

    override fun init(bundle: Bundle?) {

        header.setLeftClick { codeDialog.text("""
CardView_cardBackgroundColor 设置背景色
CardView_cardCornerRadius 设置圆角大小
CardView_cardElevation 设置z轴阴影
CardView_cardMaxElevation 设置z轴最大高度值
CardView_cardUseCompatPadding 是否使用CompatPadding
CardView_cardPreventCornerOverlap 是否使用PreventCornerOverlap
CardView_contentPadding 内容的padding
CardView_contentPaddingLeft 内容的左padding
CardView_contentPaddingTop 内容的上padding
CardView_contentPaddingRight 内容的右padding
CardView_contentPaddingBottom 内容的底padding
card_view:cardUseCompatPadding 设置内边距，V21+的版本和之前的版本仍旧具有一样的计算方式
card_view:cardPreventCornerOverlap 在V20和之前的版本中添加内边距，这个属性为了防止内容和边角的重叠
        """.trimIndent()) }

        card1.setCardBackgroundColor(Color.RED)
        card1.cardElevation = 0f
        card1.maxCardElevation = 100f
        card1.radius = 2f
        card1.setContentPadding(10,10,10,10)

        card1.click {
            ColorPickerDialogBuilder
                    .with(this)
                    .setTitle("设置CardView颜色")
                    .initialColor(currentColor)
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setOnColorSelectedListener {
                        card1.setCardBackgroundColor(it)
                        currentColor = it
                    }
                    .setPositiveButton("ok") { p0, p1, p2 ->

                    }
                    .setNegativeButton("cancel") { dialog, which -> }
                    .build()
                    .show()
        }

        sb1.change { seekBar, progress, fromUser ->
            card1.cardElevation = progress.toFloat()
        }

        sb2.progress = 2
        sb2.change { seekBar, progress, fromUser ->
            card1.radius = progress.toFloat()
        }

        sb3.change { seekBar, progress, fromUser ->
            card1.setContentPadding(progress,progress,progress,progress)
        }

        sb4.progress = 100
        sb4.change { seekBar, progress, fromUser ->
            card1.maxCardElevation = progress.toFloat()
        }

    }
}
