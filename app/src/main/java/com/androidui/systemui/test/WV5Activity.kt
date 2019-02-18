package com.androidui.systemui.test

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidui.R
import kotlinx.android.synthetic.main.activity_wv5.*

class WV5Activity : AppCompatActivity() {

    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wv5)

        webView.apply {
            loadUrl("file:///android_asset/html/demo1.html")
            settings.apply {
                javaScriptEnabled = true
                defaultTextEncodingName = "UTF-8"
            }
            //添加JS接口
            addJavascriptInterface(MyObject(this@WV5Activity),"myObj")
        }
    }
}
