package com.kotlinlib.activity

import android.graphics.Color
import android.os.Bundle
import com.android.common.dialog.CodeViewerDialog
import com.android.common.dialog.TextInputDialog
import com.android.common.dialog.WebViewerDialog
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.kotlinlib.other.BaseInterface

abstract class KotlinActivity : AbstractKotlinActivity(), BaseInterface {

    lateinit var codeDialog: CodeViewerDialog
    lateinit var webDialog:WebViewerDialog
    lateinit var textDialog: TextInputDialog

    fun colorPicker(title:String,callback:(Int)->Unit){
        ColorPickerDialogBuilder
                .with(this)
                .setTitle(title)
                .initialColor(Color.WHITE)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener {
                    callback.invoke(it)
                }
                .setPositiveButton("ok") { p0, p1, p2 ->

                }
                .setNegativeButton("cancel") { dialog, which -> }
                .build()
                .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        codeDialog = CodeViewerDialog(this)
        webDialog = WebViewerDialog(this)
        textDialog = TextInputDialog(this)
    }

}