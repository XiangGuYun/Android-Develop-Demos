package com.android.systemui.webview

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_web_view.*

@LayoutId(R.layout.activity_web_view)
class WebViewActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        btnWebView1.click {
            codeDialog.text("""
void loadUrl(String url):
加载网络链接 url

boolean canGoBack():
判断 WebView 当前是否可以返回上一页

goBack():
回退到上一页

boolean canGoForward():
判断 WebView 当前是否可以向前一页

goForward():
回退到前一页

onPause():
类似 Activity 生命周期，页面进入后台不可见状态

pauseTimers():
该方法面向全局整个应用程序的webview，它会暂停所有webview的layout，parsing，JavaScript Timer。当程序进入后台时，该方法的调用可以降低CPU功耗。

onResume():
在调用 onPause()后，可以调用该方法来恢复 WebView 的运行。

resumeTimers():
恢复pauseTimers时的所有操作。(注：pauseTimers和resumeTimers 方法必须一起使用，否则再使用其它场景下的 WebView 会有问题)

destroy():
销毁 WebView

clearHistory():
清除当前 WebView 访问的历史记录。

clearCache(boolean includeDiskFiles):
清空网页访问留下的缓存数据。需要注意的时，由于缓存是全局的，所以只要是WebView用到的缓存都会被清空，即便其他地方也会使用到。该方法接受一个参数，从命名即可看出作用。若设为false，则只清空内存里的资源缓存，而不清空磁盘里的。

reload():
重新加载当前请求

setLayerType(int layerType, Paint paint):
设置硬件加速、软件加速

removeAllViews():
清除子view。

clearSslPreferences():
清除ssl信息。

clearMatches():
清除网页查找的高亮匹配字符。

removeJavascriptInterface(String interfaceName):
删除interfaceName 对应的注入对象

addJavascriptInterface(Object object,String interfaceName):
注入 java 对象。

setVerticalScrollBarEnabled(boolean verticalScrollBarEnabled):
设置垂直方向滚动条。

setHorizontalScrollBarEnabled(boolean horizontalScrollBarEnabled):
设置横向滚动条。

loadUrl(String url, Map<String, String> additionalHttpHeaders):
加载制定url并携带http header数据。

evaluateJavascript(String script, ValueCallback<String> resultCallback):
Api 19 之后可以采用此方法之行 Js。

stopLoading():
停止 WebView 当前加载。

clearView():
在Android 4.3及其以上系统这个api被丢弃了， 并且这个api大多数情况下会有bug，经常不能清除掉之前的渲染数据。官方建议通过loadUrl("about:blank")来实现这个功能，阴雨需要重新加载一个页面自然时间会收到影响。

freeMemory():
释放内存，不过貌似不好用。

clearFormData():
清除自动完成填充的表单数据。需要注意的是，该方法仅仅清除当前表单域自动完成填充的表单数据，并不会清除WebView存储到本地的数据。

//引申--------------------

我这里在介绍下下面几组方法，比较重要，项目当中可能会遇到坑

onPause() 尽力尝试暂停可以暂停的任何处理，如动画和地理位置。 不会暂停JavaScript。 要全局暂停JavaScript，可使用pauseTimers。

onResume() 恢复onPause() 停掉的操作；

pauseTimers() 暂停所有WebView的布局，解析和JavaScript定时器。 这个是一个全局请求，不仅限于这个WebView。

resumeTimers() 恢复所有WebView的所有布局，解析和JavaScript计时器，将恢复调度所有计时器.

另外注意 JS 端setTimeout()、setInterval() 方法使用，自测来看，当不使用 pauseTimers()  和 pauseTimers() ，从 Activity 返回上一个包含WebView 的Activity时，页面里的 setTimeout() 是不执行的，setInterval() 是可以恢复执行的。

在适当的生命周期使用 pauseTimers()  和 pauseTimers() 既可以恢复setTimeout() 执行。

            """.trimIndent())
        }

        btnWebView2.click {
            codeDialog.text("""
// 加载url，也可以执行js函数
mWebView.loadUrl("http://www.jianshu.com/u/fa272f63280a");

// 设置 WebViewClient
mWebView.setWebViewClient(new SafeWebViewClient());

// 设置 WebChromeClient
mWebView.setWebChromeClient(new SafeWebChromeClient());

// 生命周期onResume
mWebView.onResume();

//生命周期resumeTimers
mWebView.resumeTimers();

//生命周期onPause
mWebView.onPause();

//生命周期pauseTimers (上数四个方法都是成对出现)
mWebView.pauseTimers();

// 停止当前加载
mWebView.stopLoading();

// 清除网页查找的高亮匹配字符。
mWebView.clearMatches();

// 清除当前 WebView 访问的历史记录
mWebView.clearHistory();

//清除ssl信息
mWebView.clearSslPreferences();

//清空网页访问留下的缓存数据。需要注意的时，由于缓存是全局的，所以只要是WebView用到的缓存都会被清空，即便其他地方也会使用到。该方法接受一个参数，从命名即可看出作用。若设为false，则只清空内存里的资源缓存，而不清空磁盘里的。
mWebView.clearCache(true);

// 清空当前加载
mWebView.loadUrl("about:blank");

// 清空子 View
mWebView.removeAllViews();

// 向 Web端注入 java 对象
if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
    mWebView.removeJavascriptInterface("AndroidNative");
}

// 生命周期销毁
mWebView.destroy();
            """.trimIndent())
        }

        btnWebSettings1.click {
            codeDialog.text("""
setJavaScriptEnabled(boolean flag):
是否支持 Js 使用。

setCacheMode(int mode):
设置 WebView 的缓存模式。

setAppCacheEnabled(boolean flag):
是否启用缓存模式。

setAppCachePath(String appCachePath):
Android 私有缓存存储，如果你不调用setAppCachePath方法，WebView将不会产生这个目录。

setSupportZoom(boolean support):
是否支持缩放。

setTextZoom(int textZoom):
Sets the text zoom of the page in percent. The default is 100。

setAllowFileAccess(boolean allow):
是否允许加载本地 html 文件/false。

setDatabaseEnabled(boolean flag):
是否开启数据库缓存

setDomStorageEnabled(boolean flag):
是否开启DOM缓存。

setUserAgentString(String ua):
设置 UserAgent 属性。

setLoadsImagesAutomatically(boolean flag):
支持自动加载图片

setAllowFileAccessFromFileURLs(boolean flag):
允许通过 file url 加载的 Javascript 读取其他的本地文件,Android 4.1 之前默认是true，在 Android 4.1 及以后默认是false,也就是禁止。

setAllowUniversalAccessFromFileURLs(boolean flag):
允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源，Android 4.1 之前默认是true，在 Android 4.1 及以后默认是false,也就是禁止如果此设置是允许，则 setAllowFileAccessFromFileURLs 不起做用。

boolean getLoadsImagesAutomatically():
是否支持自动加载图片。
            """.trimIndent())
        }

        btnWebSettings2.click {
            codeDialog.text("""
WebSettings webSettings = mWebView.getSettings();

if (webSettings == null) return;

// 支持 Js 使用
webSettings.setJavaScriptEnabled(true);

// 开启DOM缓存,默认状态下是不支持LocalStorage的
webSettings.setDomStorageEnabled(true);

// 开启数据库缓存
webSettings.setDatabaseEnabled(true);

// 支持自动加载图片
webSettings.setLoadsImagesAutomatically(hasKitkat());

// 设置 WebView 的缓存模式
webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

// 支持启用缓存模式
webSettings.setAppCacheEnabled(true);

// 设置 AppCache 最大缓存值(现在官方已经不提倡使用，已废弃)
webSettings.setAppCacheMaxSize(8 * 1024 * 1024);

// Android 私有缓存存储，如果你不调用setAppCachePath方法，WebView将不会产生这个目录
webSettings.setAppCachePath(getCacheDir().getAbsolutePath());

// 数据库路径
if (!hasKitkat()) {
    webSettings.setDatabasePath(getDatabasePath("html").getPath());
}

// 关闭密码保存提醒功能
webSettings.setSavePassword(false);

// 支持缩放
webSettings.setSupportZoom(true);

// 设置 UserAgent 属性
webSettings.setUserAgentString("");

// 允许加载本地 html 文件/false
webSettings.setAllowFileAccess(true);

// 允许通过 file url 加载的 Javascript 读取其他的本地文件,Android 4.1 之前默认是true，在 Android 4.1 及以后默认是false,也就是禁止
webSettings.setAllowFileAccessFromFileURLs(false);

// 允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源，
// Android 4.1 之前默认是true，在 Android 4.1 及以后默认是false,也就是禁止
// 如果此设置是允许，则 setAllowFileAccessFromFileURLs 不起做用
webSettings.setAllowUniversalAccessFromFileURLs(false);
            """.trimIndent())
        }

        btnWebViewClient1.click {
            codeDialog.text("""
onPageStarted(WebView view, String url, Bitmap favicon):
WebView 开始加载页面时回调，一次Frame加载对应一次回调。

onLoadResource(WebView view, String url):
WebView 加载页面资源时会回调，每一个资源产生的一次网络加载，除非本地有当前 url 对应有缓存，否则就会加载。

shouldInterceptRequest(WebView view, String url):
WebView 可以拦截某一次的 request 来返回我们自己加载的数据，这个方法在后面缓存会有很大作用。

shouldInterceptRequest(WebView view, android.webkit.WebResourceRequest request):
WebView 可以拦截某一次的 request 来返回我们自己加载的数据，这个方法在后面缓存会有很大作用。

shouldOverrideUrlLoading(WebView view, String url):
是否在 WebView 内加载页面。

onReceivedSslError(WebView view, SslErrorHandler handler, SslError error):
WebView ssl 访问证书出错，handler.cancel()取消加载，handler.proceed()对然错误也继续加载。

onPageFinished(WebView view, String url):
WebView 完成加载页面时回调，一次Frame加载对应一次回调。

onReceivedError(WebView view, int errorCode, String description, String failingUrl):
WebView 访问 url 出错。
            """.trimIndent())
        }

        btnWebViewClient2.click {
            codeDialog.text("""
public class SafeWebViewClient extends WebViewClient {

    /**
     * 当WebView得页面Scale值发生改变时回调
     */
    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
    }

    /**
     * 是否在 WebView 内加载页面
     *
     * @param view
     * @param url
     * @return
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    /**
     * WebView 开始加载页面时回调，一次Frame加载对应一次回调
     *
     * @param view
     * @param url
     * @param favicon
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    /**
     * WebView 完成加载页面时回调，一次Frame加载对应一次回调
     *
     * @param view
     * @param url
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    /**
     * WebView 加载页面资源时会回调，每一个资源产生的一次网络加载，除非本地有当前 url 对应有缓存，否则就会加载。
     *
     * @param view WebView
     * @param url  url
     */
    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    /**
     * WebView 可以拦截某一次的 request 来返回我们自己加载的数据，这个方法在后面缓存会有很大作用。
     *
     * @param view    WebView
     * @param request 当前产生 request 请求
     * @return WebResourceResponse
     */
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }

    /**
     * WebView 访问 url 出错
     *
     * @param view
     * @param request
     * @param error
     */
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
    }

    /**
     * WebView ssl 访问证书出错，handler.cancel()取消加载，handler.proceed()对然错误也继续加载
     *
     * @param view
     * @param handler
     * @param error
     */
    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
    }
}
            """.trimIndent())
        }

        btnWebChromeClient1.click {
            codeDialog.text("""
onConsoleMessage(String message, int lineNumber,String sourceID):
输出 Web 端日志。

onProgressChanged(WebView view, int newProgress):
当前 WebView 加载网页进度。

onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result):
处理 JS 中的 Prompt对话框。

onJsAlert(WebView view, String url, String message, JsResult result):
Js 中调用 alert() 函数，产生的对话框。

onReceivedTitle(WebView view, String title):
接收web页面的 Title。

onReceivedIcon(WebView view, Bitmap icon):
接收web页面的icon。
            """.trimIndent())
        }

        btnWebChromeClient2.click {
            codeDialog.text("""
public class SafeWebChromeClient extends WebChromeClient {

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return super.onConsoleMessage(consoleMessage);
    }

    /**
     * 当前 WebView 加载网页进度
     *
     * @param view
     * @param newProgress
     */
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }

    /**
     * Js 中调用 alert() 函数，产生的对话框
     *
     * @param view
     * @param url
     * @param message
     * @param result
     * @return
     */
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }

    /**
     * 处理 Js 中的 Confirm 对话框
     *
     * @param view
     * @param url
     * @param message
     * @param result
     * @return
     */
    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        return super.onJsConfirm(view, url, message, result);
    }

    /**
     * 处理 JS 中的 Prompt对话框
     *
     * @param view
     * @param url
     * @param message
     * @param defaultValue
     * @param result
     * @return
     */
    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    /**
     * 接收web页面的icon
     *
     * @param view
     * @param icon
     */
    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
    }

    /**
     * 接收web页面的 Title
     *
     * @param view
     * @param title
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
    }

}
            """.trimIndent())
        }

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


        btnTest4.click {
            go(WV4Activity::class.java)
        }

        header7.setLeftClick {
            codeDialog.text("""
首先，我们定义一个类，用于将数据暴露出来，JS通过该类暴露的方法(Public)来调用Android！

接着，我们在WebView所在页面Activity，使用下述代码:
webView.getSettings().setJavaScriptEnabled(true);
webView.addJavascriptInterface(object,"name");

然后js或者html中调用name.xxx调用对象里的暴露的方法：
比如： <input type="button" value="Toast提示" onclick="name.showToast('呵呵');"/>
另外，setJavaScriptEnabled是在Android4.4以前的系统才有效！！！

//------------------------------

注：这里着重介绍下第一种标准方式，后面会介绍其他两种方式。

1、使用系统方法 addJavascriptInterface 注入 java 对象来实现。

2、利用 WebViewClient 中 shouldOverrideUrlLoading (WebView view, String url) 接口，拦截操作。这个就是很多公司在用的 scheme  方式，通过制定url协议，双方各自解析，使用iframe来调用native代码，实现互通。

3、利用 WebChromeClient 中的 onJsAlert、onJsConfirm、onJsPrompt 提示接口，同样也是拦截操作。

使用清单：
//开启Js可用
mWebView.getSettings().setJavaScriptEnabled(true);

// 创建要注入的 Java 类
public class NativeInterface {

    private Context mContext;

    public NativeInterface(Context context) {
        mContext = context;
    }

    @JavascriptInterface
    public void hello() {
        toast("hello")
    }

    @JavascriptInterface
    public void hello(String params) {
        toast(params)
    }

    @JavascriptInterface
    public String getAndroid() {
        toast("getAndroid")
        return "Android data";
    }

}

// WebView 注入即可
mWebView.addJavascriptInterface(new NativeInterface(this), "AndroidNative");

//Js编写
<script>
    function callHello(){
        AndroidNative.hello();
    }

    function callHello1(){
        AndroidNative.hello('hello Android');
    }

    function callAndroid(){
        var temp = AndroidNative.getAndroid();
        console.log(temp);
        alert(temp);
    }

</script>

Native 调用 Js：mWebView.loadUrl(js);
Js 调用 Native :AndroidNative.getAndroid();

4.2版本以下会存在漏洞，4.2以上需要添加 @JavascriptInterface 注解才能被调用到，Js 调用方式不变。
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
/**
* 中介类，提供方法给JS调用
*/
public class MyObject {

    private Context context;

    public MyObject(Context context) {
        this.context = context;
    }

    //将显示Toast和对话框的方法暴露给JS脚本调用
    @JavascriptInterface
    public void showToast(String name) {
        toast(name)
    }

    @JavascriptInterface
    public void showDialog() {
        new AlertDialog.Builder(context)
                .setTitle("联系人列表")
                .setIcon(R.mipmap.ic_launcher_round)
                .setItems(new String[]{"1", "2", "3", "4", "5"}, null)
                .setPositiveButton("确定", null)
                .create()
                .show();
    }
}

/**
* Activity
*/
class WV5Activity : AppCompatActivity() {

    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wv5)

        webView.apply {
            //加载URL
            loadUrl("file:///android_asset/html/demo1.html")

            //设置参数
            settings.apply {
                javaScriptEnabled = true//必需
                defaultTextEncodingName = "UTF-8"
            }

            //添加JS接口
            //第一个参数是中介类对象
            //第二个参数是随意定义的字符串，指代中介类在JS中的对象
            addJavascriptInterface(MyObject(this),"myObj")
        }
    }
}

/**
* Html文件
*/
<html>

<head>
    <title>Js调用Android</title>
</head>

<body>

<input type="button" value="Toast提示" onclick="myObj.showToast('Hello Android');"/>

<input type="button" value="列表对话框" onclick="myObj.showDialog();"/>

</body>

</html>
            """.trimIndent())
        }

        btnTest7.click {
            go(WV5Activity::class.java)
        }

    }

}
