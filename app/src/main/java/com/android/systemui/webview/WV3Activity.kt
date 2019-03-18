package com.android.systemui.webview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.android.R
import kotlinx.android.synthetic.main.activity_wv3.*


class WV3Activity : AppCompatActivity() {

    private var exitTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wv3)

        webView.loadUrl("http://www.hao123.com")
        webView.webViewClient = object :WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        webView.setOnScrollChangedCallback { dx, dy ->
            if(dy>0){
                btnTop.visibility = View.VISIBLE
            }else{
                btnTop.visibility = View.GONE
            }
        }

        btnTop.setOnClickListener {
            webView.scrollY = 0
            btnTop.visibility = View.GONE
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
