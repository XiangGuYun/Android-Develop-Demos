package com.android.systemui.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.android.R
import kotlinx.android.synthetic.main.activity_wv3.*

class WV4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wv4)

        val settings = webView.settings
        settings.useWideViewPort = true//设定支持viewport
        settings.loadWithOverviewMode = true   //自适应屏幕
        settings.builtInZoomControls = true
        settings.displayZoomControls = false//取消显示缩放控件
        settings.setSupportZoom(true)//设定支持缩放

        webView.loadUrl("http://www.runoob.com/")
        webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

    }
}
