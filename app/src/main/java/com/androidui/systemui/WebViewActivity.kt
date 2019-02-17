package com.androidui.systemui

import android.os.Bundle
import com.androidui.R
import com.androidui.systemui.test.WV1Activity
import com.androidui.systemui.test.WV2Activity
import com.androidui.systemui.test.WV3Activity
import com.androidui.systemui.test.WV4Activity
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_web_view.*

@LayoutId(R.layout.activity_web_view)
class WebViewActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        btnTest1.click { go(WV1Activity::class.java) }

        header1.setRightClick {
            codeDialog.text("""
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
            """.trimIndent())
        }

        btnTest2.click {
            go(WV2Activity::class.java)
        }

        btnTest3.click {
            go(WV3Activity::class.java)
        }

        header.setLeftClick {
            codeDialog.text("""
onJsAlert(WebView view,String url,String message,JsResult result)	处理Js中的Alert对话框

onJsConfirm(WebView view,String url,String message,JsResult result)	处理Js中的Confirm对话框

onJsPrompt(WebView view,String url,String message,String defaultValue,JsPromptResult result)	处理Js中的Prompt对话框

onProgressChanged(WebView view,int newProgress)	当加载进度条发生改变时调用

onReceivedIcon(WebView view, Bitmap icon) 获得网页的icon

onReceivedTitle(WebView view, String title)	获得网页的标题
            """.trimIndent())
        }

        header3.setLeftClick {
            codeDialog.text("""
                我们都知道监听滚动事件一般都是设置setOnScrollChangedListener，可惜的是 WebView并没有给我们提供这样的方法，但是我们可以重写WebView，覆盖里面的一个方法： protected void onScrollChanged(final int l, final int t, final int oldl,final int oldt){} 然后再对外提供一个接口

                顺便提下滚动条设置的问题
                setHorizontalScrollBarEnabled(false);//水平不显示
                setVerticalScrollBarEnabled(false); //垂直不显示
                setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);//滚动条在WebView内侧显示
                setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)//滚动条在WebView外侧显示
            """.trimIndent())
        }

        header4.setLeftClick {
            codeDialog.text("""
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);//设定支持viewport
        settings.setLoadWithOverviewMode(true);   //自适应屏幕
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);//取消显示缩放控件
        settings.setSupportZoom(true);//设定支持缩放

        我们也可以自行设置初始的缩放比例，只需为webView：
        webView.setInitialScale(25)//为25%，最小缩放等级

        可能有时我们仅仅是需要对字体进行缩放，那么可以这样做：
        settings.setTextZoom(int)
        也可以直接通过settings.setTextSize(TextSize.LARGER)来设置大小。
        Android自带五个可选字体大小的值：SMALLEST(50%),SMALLER(75%),NORMAL(100%),LARGER(150%), LARGEST(200%)。

            """.trimIndent())
        }

        header5.setLeftClick {
            codeDialog.text("""
我们都知道Cookie其实只是一个代表用户唯一标识的字符串，情景一般是： 用户输入账号密码后，点击登陆，用户要拿着这个Cookie去访问服务器提供的相关服务！ 我们可以把cookie的获取写到onPageFinsihed的方法中，简单的可以这样写：

@Override
public void onPageFinished(WebView view, String url) {
    CookieManager cookieManager = CookieManager.getInstance();
    String CookieStr = cookieManager.getCookie(url);
    Log.e("HEHE", "Cookies = " + CookieStr);
    super.onPageFinished(view, url);
}
            """.trimIndent())
        }

        btnTest4.click {
            go(WV4Activity::class.java)
        }

    }

}
