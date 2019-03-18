package com.android.systemui.webview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.android.R
import kotlinx.android.synthetic.main.activity_wv2.*


class WV2Activity : AppCompatActivity() {

    private var exitTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wv2)

        webView.loadUrl("http://www.baidu.com")
        webView.webChromeClient = object :WebChromeClient(){
            //这里设置获取到的网站title
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                tvTitle.text = title
            }
        }

        webView.webViewClient = object :WebViewClient(){
            //在webview里打开新链接
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return false
            }
        }

        btnBack.setOnClickListener {
            finish()
        }

        btnRefresh.setOnClickListener {
            webView.reload()//刷新当前页面
        }

        btnTop.setOnClickListener {
            webView.scrollY = 0//滚动到顶部
        }

    }

    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        }else{
            if((System.currentTimeMillis() - exitTime)>2000){
                Toast.makeText(applicationContext, "再按一次退出程序",
                        Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis()
            }else{
                finish()
            }
        }
    }

}
