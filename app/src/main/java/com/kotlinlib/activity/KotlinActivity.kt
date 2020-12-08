package com.kotlinlib.activity

import android.graphics.Color
import android.os.Bundle
import com.android.common.dialog.CodeViewerDialog
import com.android.common.dialog.TextInputDialog
import com.android.common.dialog.WebViewerDialog
import com.kotlinlib.other.BaseInterface

abstract class KotlinActivity : AbstractKotlinActivity(), BaseInterface {

    lateinit var codeDialog: CodeViewerDialog
    lateinit var webDialog:WebViewerDialog
    lateinit var textDialog: TextInputDialog

    fun colorPicker(title:String,callback:(Int)->Unit){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        codeDialog = CodeViewerDialog(this)
        webDialog = WebViewerDialog(this)
        textDialog = TextInputDialog(this)
    }

}