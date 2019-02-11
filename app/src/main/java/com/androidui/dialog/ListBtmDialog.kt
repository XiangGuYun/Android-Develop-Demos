package com.androidui.dialog

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import com.androidui.R
import com.kotlinlib.RV
import com.kotlinlib.TV
import com.kotlinlib.dialog.BottomDialog
import com.kotlinlib.view.recyclerview.RVInterface
import com.kotlinlib.view.recyclerview.RVUtils
import java.util.*

/**
 * 底部对话框（含标题和文字列表）
 */
class ListBtmDialog(ctx: Context, title: String, items: ArrayList<String>, getItemView: (dialog: Dialog, view: TextView, pos: Int) -> Unit) : RVInterface {

    var dialog: BottomDialog

    init {
        dialog = BottomDialog(ctx, R.layout.dialog_btm_menu, BottomDialog.OnGetDialog { dialog, dialogView ->
            val rv = dialogView.findViewById<RV>(R.id.rvDialog)
            dialogView.findViewById<TV>(R.id.tvDialog).text = title
            RVUtils(rv).rvAdapter(items, { holder, pos ->
                holder.setText(R.id.tvItem, items[pos])
                getItemView.invoke(dialog, holder.getItemView().findViewById(R.id.tvItem), pos)
            }, R.layout.cell_dialog)
        })
    }

    fun dismiss() {
        dialog.dialog.dismiss()
    }

    fun show() {
        dialog.dialog.show()
    }

    fun getBtmDialog(): Dialog {
        return dialog.dialog
    }

}
