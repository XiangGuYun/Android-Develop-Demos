package com.androidui.dialog

import android.content.Context
import android.text.Html
import android.text.Spannable
import android.text.Spanned
import com.androidui.R
import com.kotlinlib.dialog.DialogInfo
import com.kotlinlib.other.KotlinDialog

//代码阅读对话框
@DialogInfo(350,440, R.layout.dialog_info_web)
class WebViewerDialog(ctx: Context): KotlinDialog(ctx){
    init {
        dv.tv(R.id.tvYes).setOnClickListener {
            dismiss()
        }
        setCancelable(false)
        setOnDismissListener {  }
    }

    fun url(file:String){
        val web = dv.wv(R.id.webView)
        web.settings.textZoom = 70
//        web.settings.apply {
//            useWideViewPort = true
//            loadWithOverviewMode = true
//            builtInZoomControls = true
//            displayZoomControls = false
//            textZoom = 200
//        }.setSupportZoom(true)

        web.loadUrl("file:///android_asset/html/$file.html")
        show()
    }


}



