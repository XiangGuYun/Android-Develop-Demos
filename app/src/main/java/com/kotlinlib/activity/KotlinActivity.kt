package com.kotlinlib.activity

import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.Toast
import com.android.common.dialog.CodeViewerDialog
import com.android.common.dialog.TextInputDialog
import com.android.common.dialog.WebViewerDialog
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kotlinlib.other.BaseInterface
import com.kotlinlib.other.DensityUtils
import com.kotlinlib.other.LayoutId
import org.greenrobot.eventbus.EventBus

open abstract class KotlinActivity : AppCompatActivity(), BaseInterface {

    val ACTIVITY_NAME = "ac_name"
    var startEventBus = false

    fun colorPicker(title:String,callback:(Int)->Unit){
        ColorPickerDialogBuilder
                .with(this)
                .setTitle(title)
                .initialColor(Color.WHITE)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener {
                    callback.invoke(it)
                }
                .setPositiveButton("ok") { p0, p1, p2 ->

                }
                .setNegativeButton("cancel") { dialog, which -> }
                .build()
                .show()
    }

    companion object {
        var gson = Gson()//All activities share a GSON
        var actList = ArrayList<Activity>()
        var currAct:String = ""
        /**
         * Finish the Activity by its name
         * @param actName String
         */
        fun finishActivityByName(actName:String){
            for(activity in actList){
                Log.e("TTTT", activity.javaClass.simpleName)
                if(activity.javaClass.simpleName==actName){
                    activity.finish()
                    return
                }
            }
        }

        fun finishAllActivities(){
            for(activity in actList){
                activity.finish()
            }
        }
    }

    fun setAndroidNativeLightStatusBar(activity:Activity, dark:Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val decor = activity.window.decorView
            if (dark) {
                decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
        }
    }

    /**
     * 设置状态栏颜色，如要调用setAndroidNativeLightStatusBar，则必须靠后调用
     * @param statusColor Int
     */
    fun setStatusBarColor(statusColor:Int){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            //取消状态栏透明
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //添加Flag把状态栏设为可绘制模式
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            //设置状态栏颜色
            window.statusBarColor = statusColor
            //设置系统状态栏处于可见状态
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE;
            //让view不根据系统窗口来调整自己的布局
            val mContentView = window.findViewById(Window.ID_ANDROID_CONTENT) as ViewGroup
            val mChildView = mContentView.getChildAt(0)
            ViewCompat.setFitsSystemWindows(mChildView, false)
            ViewCompat.requestApplyInsets(mChildView)
        }
    }

    lateinit var codeDialog: CodeViewerDialog
    lateinit var webDialog:WebViewerDialog

    lateinit var textDialog: TextInputDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val viewInject = this::class.annotations[0] as LayoutId
        setContentView(viewInject.id)
        "The Current Activity is ${this.javaClass.simpleName}".logD(ACTIVITY_NAME)
        init(savedInstanceState)
        if(startEventBus){
            EventBus.getDefault().register(this)
        }
        actList.add(this)
        codeDialog = CodeViewerDialog(this)
        webDialog = WebViewerDialog(this)
        textDialog = TextInputDialog(this)
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected fun isImmersionBarEnabled(): Boolean {
        return true
    }

    override fun onResume() {
        super.onResume()
        currAct = javaClass.simpleName
    }

    override fun onDestroy() {
        actList.remove(this)
        if(startEventBus){
            EventBus.getDefault().unregister(this)
        }
        super.onDestroy()
    }

    protected abstract fun init(bundle: Bundle?)

    /**
     * Gets the object JSON string
     * @param any Any
     * @return String
     */
    fun jsonStr(any: Any):String{
        return gson.toJson(any)
    }

    fun Any.toast(isLong: Boolean=false, pre:String=""){
        if(isLong)
            Toast.makeText(this@KotlinActivity, "$pre${this}",
                    Toast.LENGTH_SHORT).apply {
                setGravity(Gravity.CENTER, 0, 0)
            }.show()
        else
            Toast.makeText(this@KotlinActivity,"$pre${this}",
                    Toast.LENGTH_SHORT).apply {
                setGravity(Gravity.CENTER, 0, 0)
            }.show()
    }



    /**
     * Dimensional unit conversion
     */
    fun Number.px2dp():Int{
        return DensityUtils.px2dip(this@KotlinActivity, this.toFloat())
    }

    fun Number.dp2px():Int{
        return DensityUtils.dip2px(this@KotlinActivity, this.toFloat())
    }

    fun Number.sp():Int{
        return DensityUtils.px2sp(this@KotlinActivity, this.toFloat())
    }

    fun Number.px():Int{
        return DensityUtils.sp2px(this@KotlinActivity, this.toFloat())
    }

    /**
     * Greys the background by changing the transparency of the window
     * @param alpha Float
     */
    fun windowAlpha(alpha:Float=0.4f){
        val attr = window.attributes
        attr.alpha = alpha
        window.attributes = attr
    }

    class JsonList<T>{
        /**
         * Convert the json string to an object List
         */
        fun transList(jsonStr:String): List<T> {
            return gson.fromJson(jsonStr, object : TypeToken<List<T>>(){}.type) as List<T>
        }
    }

    fun inf(id:Int): View {
       return inflater.inflate(id, null)
    }



}