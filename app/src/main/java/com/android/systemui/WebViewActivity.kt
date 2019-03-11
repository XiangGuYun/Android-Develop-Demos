package com.android.systemui

import android.os.Bundle
import com.android.R
import com.android.systemui.test.*
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

        header6.setLeftClick {
            codeDialog.text("""
如何为WebView设置Cookie呢？ 我们可以在需要设置Cookie的地方加入下述代码：
CookieSyncManager.createInstance(TextureViewCase1Activity.this);
CookieManager cookieManager = CookieManager.getInstance();
cookieManager.setAcceptCookie(true);
cookieManager.setCookie(url, cookies);  //cookies是要设置的cookie字符串
CookieSyncManager.getInstance().sync();
            """.trimIndent())
        }

        header7.setLeftClick {
            codeDialog.text("""
首先，我们定义一个类，用于将数据暴露出来，JS通过该类暴露的方法(Public)来调用Android！

接着，我们在WebView所在页面Activity，使用下述代码:
webView.getSettings().setJavaScriptEnabled(true);
webView.addJavascriptInterface(object,"name");

然后js或者html中调用name.xxx调用对象里的暴露的方法：
比如： < input type="button" value="Toast提示" onclick="name.showToast('呵呵');"/>
另外，setJavaScriptEnabled是在Android 4.4以前的系统才有效！！！
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
public class MyObject {
    private Context context;
    public MyObject(Context context) {
        this.context = context;
    }

    //将显示Toast和对话框的方法暴露给JS脚本调用
    @JavascriptInterface
    public void showToast(String name) {
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void showDialog() {
        new AlertDialog.Builder(context)
                .setTitle("联系人列表").setIcon(R.mipmap.ic_launcher_round)
                .setItems(new String[]{"基神", "B神", "曹神", "街神", "翔神"}, null)
                .setPositiveButton("确定", null).create().show();
    }
}

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
            """.trimIndent())
        }

        btnTest7.click {
            go(WV5Activity::class.java)
        }

    }

}
