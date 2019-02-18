package com.androidui.dialog

import android.content.Context
import com.androidui.R
import com.kotlinlib.dialog.DialogInfo
import com.kotlinlib.other.KotlinDialog

//代码阅读对话框
@DialogInfo(320,400, R.layout.dialog_info)
class CodeViewerDialog(ctx: Context): KotlinDialog(ctx){
    init {
        dv.tv(R.id.tvYes).setOnClickListener {
            dismiss()
        }
        setCancelable(false)
        setOnDismissListener { dv.tv(R.id.tvMain).text = "" }
    }

    fun text(text:String){
        dv.tv(R.id.tvMain).text = text
        show()
    }

}



