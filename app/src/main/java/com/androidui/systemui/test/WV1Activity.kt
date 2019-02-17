package com.androidui.systemui.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.androidui.R

/**
 * 直接在Activity上加载一个WebView
 */
class WV1Activity : AppCompatActivity() {

    lateinit var webView:WebView
    var exitTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wv1)
        webView = WebView(this)
        webView.webViewClient = object : WebViewClient() {
            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        webView.settings.javaScriptEnabled = true //设置WebView属性,运行执行js脚本
        webView.loadUrl("http://www.baidu.com") //调用loadUrl方法为WebView加入链接
        setContentView(webView) //调用Activity提供的setContentView将webView显示出来

    }

    //1.webView.canGoBack()判断网页是否能后退,可以则goback()
    //2.如果不可以连续点击两次退出App,否则弹出提示Toast
    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        }else{
            if((System.currentTimeMillis()-exitTime)>2000){
                Toast.makeText(this, "再按一次退出程序",
                        Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis()
            } else {
                super.onBackPressed()
            }
        }
    }

}
