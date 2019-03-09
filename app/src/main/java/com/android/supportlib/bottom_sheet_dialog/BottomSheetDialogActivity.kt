package com.android.supportlib.bottom_sheet_dialog

import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.view.View
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_bottom_sheet_dialog.*
import kotlinx.android.synthetic.main.bottom_dialog_view.view.*

@LayoutId(R.layout.activity_bottom_sheet_dialog)
class BottomSheetDialogActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        val dialog = BottomSheetDialog(this)

        val view1 = inf(R.layout.bottom_dialog_view)

        RVUtils(view1.rvTest).rvAdapter((1..5).toList(),{
            holder, pos ->
            holder.text(R.id.tvCell, "Item${pos+1}")
            holder.itemClick {
                "Item${pos+1}".toast()
                dialog.dismiss()
            }
        }, R.layout.item_tv)

        dialog.setContentView(view1)

        //设置对话框背景为透明，必须放在setContentView之后
        dialog.delegate.findViewById<View>(android.support.design.R.id.design_bottom_sheet)
                ?.setBackgroundColor(resources.getColor(android.R.color.transparent))

        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)

        btn1.click {
            dialog.show()
        }

        header1.setRightClick {
            codeDialog.text("""
val dialog = BottomSheetDialog(this)

val view1 = inf(R.layout.bottom_dialog_view)

RVUtils(view1.rvTest).rvAdapter((1..5).toList(),{
    holder, pos ->
    holder.text(R.id.tvCell, "Item$'{pos+1}")
    holder.itemClick {
        "Item$'{pos+1}".toast()
        dialog.dismiss()
    }
}, R.layout.item_tv)

dialog.setContentView(view1)

//设置对话框背景为透明，必须放在setContentView之后
dialog.delegate.findViewById<View>(android.support.design.R.id.design_bottom_sheet)
        ?.setBackgroundColor(resources.getColor(android.R.color.transparent))

dialog.setCanceledOnTouchOutside(false)
dialog.setCancelable(false)

btn1.click {
    dialog.show()
}
            """.trimIndent())
        }

    }

}
