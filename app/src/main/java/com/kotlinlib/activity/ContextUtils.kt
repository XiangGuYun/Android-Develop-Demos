package com.kotlinlib.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import com.tbruyelle.rxpermissions2.RxPermissions
import java.io.Serializable
import android.support.v4.content.ContextCompat.getSystemService
import android.widget.EditText
import android.support.v4.content.ContextCompat.getSystemService
import java.util.*


/**
 * 上下文扩展类
 * 让Context可以做更多事情
 * 以下所有变量和方法都可以在Activity中直接调用，无需借助任何工具类
 */
interface ContextUtils {

    //布局填充器
    val Context.inflater: LayoutInflater get()=LayoutInflater.from(this)

    //SDCard路径
    val Context.sdcard_path: String get() = Environment.getExternalStorageDirectory().toString()

    /**
     * 获取一个Drawable
     */
    fun Context.drawable(id:Int): Drawable? {
        return resources.getDrawable(id)
    }

    /*
    3.软键盘把某些布局挤上去了的情况
    <activity
        android:name=".activity.DetailActivity"
        android:screenOrientation="portrait"
        android:windowSoftInputMode="adjustPan"
        android:theme="@style/AppTheme.NoActionBar.Translucent"/>
    主要就是 windowSoftInputMode 这个属性，其中2个比较重要的是 adjustPan 和 adjustResize
    adjustPan 不会把底部的布局给挤上去，例如relateLayout 布局中 放到bottom 的布局
    adjustResize 是自适应的，会把底部的挤上去。
     */

    /**
     * 自动弹出键盘
     * @receiver Context
     * @param et EditText
     */
    fun Context.showKeyboard(et:EditText) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager!!.showSoftInput(et, 0)
    }

    /**
     * 延迟弹出键盘
     * @receiver Context
     * @param et EditText
     * @param delayedTime Long
     */
    fun Context.showKeyboardDelay(et:EditText, delayedTime:Long=1000){
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val inputManager = et.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.showSoftInput(et, 0)
            }
        }, delayedTime)
    }


    /**
     * 切换键盘
     */
    fun Context.toggleKeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm!!.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    /**
     * 关闭键盘
     */
    fun Activity.closeKeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if(imm.isActive&&currentFocus!=null){
            if(currentFocus.windowToken!= null){
                imm.hideSoftInputFromWindow(this.currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }

    /**
     * 获取屏幕宽度
     */
    val Context.srnWidth
        get() = this.resources.displayMetrics.widthPixels

    /**
     * 获取屏幕高度
     */
    val Context.srnHeight
        get() = this.resources.displayMetrics.heightPixels

    /**
     * 复制文字到剪贴板
     */
    fun Context.copyStringToChipboard(text:String){
        val myClipboard: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val  myClip: ClipData = ClipData.newPlainText("text", text)
        myClipboard.primaryClip = myClip
    }

    /**
     * 跳转到目标Activity，并可携带参数
     * @param cls Class<T> 目标Activity的class
     * @param pairs Array<out Pair<String, Any>> 携带参数
     */
    fun <T: Activity> Activity.go(cls:Class<T>, vararg pairs:Pair<String,Any>){
        val intent = Intent(this,cls)
        pairs.forEach {
            when(it.second::class){
                String::class->{
                    intent.putExtra(it.first,it.second.toString())
                }
                Int::class->{
                    intent.putExtra(it.first,it.second as Int)
                }
                Boolean::class->{
                    intent.putExtra(it.first,it.second as Boolean)
                }
                Double::class->{
                    intent.putExtra(it.first,it.second as Double)
                }
//                Serializable::class->intent.putExtra(it.first,it.second as Serializable) 无效
            }
            Log.d("T_BUNDLE", ("${it.first} ${it.second}"))
        }
        startActivity(intent)
    }

    /**
     * 获取Bundle字符串
     * @receiver Activity
     * @param name String
     * @return String?
     */
    fun Activity.extraStr(name:String): String? {
        return intent.getStringExtra(name)
    }

    /**
     * 获取Bundle整型
     * @receiver Activity
     * @param pair Pair<String,Int>
     * @return Int
     */
    fun Activity.extraInt(pair:Pair<String,Int>): Int {
        return intent.getIntExtra(pair.first,pair.second)
    }

    /**
     * 获取Bundle布尔值
     * @receiver Activity
     * @param pair Pair<String,Boolean>
     * @return Boolean
     */
    fun Activity.extraBool(pair:Pair<String,Boolean>):Boolean{
        return intent.getBooleanExtra(pair.first,pair.second)
    }

    /**
     * 获取Bundle序列化
     * 注意内部类也必须实现序列化，否则无法收到
     * @receiver Activity
     * @param name String
     * @return Serializable?
     */
    fun Activity.extraSerial(name:String): Serializable? {
        return intent.getSerializableExtra(name)
    }

            /**
     * 跳转拨号界面 <uses-permission android:name="android.permission.CALL_PHONE" />
     * @receiver KotlinActivity
     * @param number String 号码
     * @param isCall Boolean 是否直接拨打 默认不
     */
    fun Activity.goToDial(number:String, isCall:Boolean=false){
        reqPermission({
            val intent = if (!isCall) Intent(Intent.ACTION_DIAL) else Intent(Intent.ACTION_CALL)
            val data = Uri.parse("tel:$number")
            intent.data = data
            startActivity(intent)
        },{

        }, Manifest.permission.CALL_PHONE)

    }

    /**
     * 请求权限
     * @receiver Activity
     * @param yes ()->Unit 成功回调
     * @param no ()->Unit 失败回调
     * @param perm Array<out String> 权限可变数组
     */
    fun Activity.reqPermission(yes:()->Unit,no:()->Unit,vararg perm:String){
        RxPermissions(this)
                .request(*perm)
                .subscribe { granted ->
                    if (granted) {
                        yes.invoke()
                    } else {
                        no.invoke()
                    }
                }
    }




}