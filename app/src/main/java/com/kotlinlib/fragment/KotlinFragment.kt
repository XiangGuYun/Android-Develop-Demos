package com.kotlinlib.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.BaseInterface
import com.kotlinlib.other.DensityUtils
import com.kotlinlib.other.LayoutId

abstract class KotlinFragment:Fragment(), BaseInterface{

    companion object {
        lateinit var gson:Gson
    }

    fun getAct(): KotlinActivity {
        return activity as KotlinActivity
    }

    lateinit var fragView:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewInject = this::class.annotations[0] as LayoutId
        fragView = inflater.inflate(viewInject.id,container,false)
        gson = KotlinActivity.gson
        return fragView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    abstract fun init()

    /**
     * 获取对象JSON字符串
     * @param any Any
     * @return String
     */
    fun jsonStr(any: Any):String{
        return gson.toJson(any)
    }

    /**
     * 土司提示
     * @param isLong 是否显示更长时间
     */
    fun Any.toast(isLong: Boolean=false){
        if(isLong)
            Toast.makeText(activity,this.toString(),
                    Toast.LENGTH_SHORT).apply {
                setGravity(Gravity.CENTER, 0, 0)
            }.show()
        else
            Toast.makeText(activity,this.toString(),
                    Toast.LENGTH_SHORT).apply {
                setGravity(Gravity.CENTER, 0, 0)
            }.show()
    }

    /**
     * 尺寸单位转换
     */
    fun Number.px2dp():Int{
        return DensityUtils.px2dip(this@KotlinFragment.activity as Context, this.toFloat())
    }

    fun Number.dp2px():Int{
        return DensityUtils.dip2px(this@KotlinFragment.activity as Context, this.toFloat())
    }

    fun Number.sp():Int{
        return DensityUtils.px2sp(this@KotlinFragment.activity as Context, this.toFloat())
    }

    fun Number.px():Int{
        return DensityUtils.sp2px(this@KotlinFragment.activity as Context, this.toFloat())
    }

    inner class JsonList<T>{
        fun transList(jsonStr:String): List<T> {
            return gson.fromJson(jsonStr, object : TypeToken<List<T>>(){}.type) as List<T>
        }

        fun transArrayList(jsonStr:String): ArrayList<T> {
            return gson.fromJson(jsonStr, object : TypeToken<ArrayList<T>>(){}.type) as ArrayList<T>
        }
    }

}