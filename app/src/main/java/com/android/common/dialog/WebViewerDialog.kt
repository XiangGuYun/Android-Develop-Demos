package com.android.common.dialog

import android.content.Context
import com.android.R
import com.kotlinlib.dialog.DialogInfo
import com.kotlinlib.other.KotlinDialog
import com.kotlinlib.view.imageview.ZoomImageView

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

    fun url(file:String, textZoom:Int=70){
        val web = dv.wv(R.id.webView)
        web.settings.textZoom = textZoom
        web.settings.apply {
//            useWideViewPort = true
//            loadWithOverviewMode = true
            builtInZoomControls = true
            displayZoomControls = false
        }.setSupportZoom(true)

        web.loadUrl("file:///android_asset/html/$file.html")
        show()
    }


}



