package com.android.common.dialog

import android.content.Context
import android.text.InputType
import com.android.R
import com.kotlinlib.dialog.DialogInfo
import com.kotlinlib.other.KotlinDialog
import kotlinx.android.synthetic.main.dialog_input.view.*

//代码阅读对话框
@DialogInfo(240,150, R.layout.dialog_input_text)
class TextInputDialog(ctx: Context): KotlinDialog(ctx){
    init {
        dv.tv(R.id.tvYes).setOnClickListener {
            dismiss()
        }
        dv.et(R.id.etMain).inputType = InputType.TYPE_CLASS_TEXT
        setOnDismissListener { dv.tv(R.id.etMain).text = "" }
    }

    fun hint(text:String){
        dv.et(R.id.etMain).hint = text
        show()
    }

    fun text(): String {
        return dv.tv(R.id.etMain).textString
    }

    fun submit(getText:(text:String)->Unit){
        dv.tvYes.setOnClickListener {
            if(text().isNotEmpty()&&text().isNumOnly()){
                getText.invoke(text())
                dismiss()
            }else{
                "输入内容不对".toast(dv.context)
            }
        }
    }

}



