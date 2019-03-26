package com.kotlinlib.other

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

/**
 * String扩展类
 */
interface StringUtils {

    val DEBUG: Int
        get() = 1
    val ERROR: Int
        get() = 3

    /**
     * 加密手机号码
     * @receiver String
     * @param phone String
     * @return String
     */
    fun String.encryptPhone(): String {
        return if (length == 11)
            this.replace(this.subSequence(3, 7).toString(), "****")
        else
            this
    }

    fun String.encryptIDNumber(): String {
        return if (length == 18 || length == 15)
            this.replace(this.subSequence(6, 14).toString(), "********")
        else
            this
    }

    /**
     * 打印日志
     */
    fun Any.logD(tag: String = "def", pre: String = "") {
        Log.d(tag, pre + this.toString())
    }

    /**
     * 打印日志
     */
    fun Any.logI(tag: String = "def") {
        Log.i(tag, this.toString())
    }

    /**
     * 打印日志
     */
    fun Any.logE(tag: String = "def", pre: String = "") {
        Log.e(tag, pre + this.toString())
    }

    /**
     * 获取颜色
     */
    fun String.color(): Int {
        return Color.parseColor(this)
    }

    /**
     * 将时间长整型转换为特定格式的时间
     * @receiver Long
     * @param fmt String 如yyyy-MM-dd
     * @return String 如2008-10-01
     */
    @SuppressLint("SimpleDateFormat")
    fun Long.fmtDate(fmt: String): String {
        return SimpleDateFormat(fmt).format(Date(this))
    }

    fun Int.ifEmpty(): String {
        return if (this == 0)
            ""
        else
            this.toString()
    }

    fun String.delP00(): String {
        return replace(".000", "")
    }

    /**
     * 将时间字符串转换为特定格式的时间
     * @receiver Long
     * @param fmt String 如yyyy-MM-dd
     * @return String 如2008-10-01
     */
    fun String.fmtDate(fmt: String): String {
        return SimpleDateFormat(fmt).format(Date(this.toLong()))
    }

    /**
     * 避免空字符串带来的困扰
     * @receiver String?
     * @return String
     */
    fun String?.getString(): String {
        return if (!this.isNullOrEmpty())
            this.toString()
        else
            ""
    }

    fun String?.getNumString(): Int {
        return if (!this.isNullOrEmpty())
            this!!.toInt()
        else
            71
    }

    /**
     * 格式化字符串
     */
    fun String?.valideString(): String {
        return when {
            this.isNullOrEmpty() -> "-"
            else -> this.toString()
        }
    }
    /**
     * 格式化字符串
     */
    fun String?.valideString2Empty(): String {
        return when {
            this.isNullOrEmpty() -> ""
            else -> this.toString()
        }
    }
    fun String?.formatMoneyData(): Float{
        return when{
            this.isNullOrEmpty() -> 0.0f
            else ->{
                try {
                    this!!.toFloat()
                }catch (e:Exception){
                    e.printStackTrace()
                    0.0f
                }
            }
        }
    }

    fun String?.formatIntegerData(): Int{
        return when {
            this.isNullOrEmpty() -> 0
            else -> this!!.toInt()
        }
    }

    /**
     * 判断是否只有数字
     */
    fun String.isNumOnly(): Boolean {
        return TextUtils.isDigitsOnly(this)
    }

    fun String.toast(ctx:Context){
        Toast.makeText(ctx, this, Toast.LENGTH_SHORT).show()
    }

    /**
     * 转为UTF-8
     */
    fun String.utf8(): String {
        try {
            return URLEncoder.encode(this, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("UnsupportedEncodingException occurred. ", e)
        }
    }

    /**
     * 将某段文本进行另类颜色处理
     * @receiver String
     * @param color String
     * @return String
     */
    fun String.htmlColor(color: String): String {
        return "<font color='$color'>$this</font>"
    }

    /**
     * 将对象集合的某一字符串字段进行拼接
     * @param list ArrayList<T> 对象集合
     * @param regex String 分隔符
     * @param func (Int)->String 根据集合索引来获取字符串
     * @return String
     */
    fun <T> appendStr(list: ArrayList<T>, regex: String, func: (Int) -> String): String {
        val build = StringBuilder()
        for (i in list.indices) {
            if (i != list.size - 1) {
                build.append(func.invoke(i)).append(regex)
            } else {
                build.append(func.invoke(i))
            }
        }
        return build.toString()
    }


    /**
     * 获取是随机颜色字符串
     */
    fun getRandColorCode(): String {
        var r: String
        var g: String
        var b: String
        r = Integer.toHexString(Random.nextInt(256)).toUpperCase()
        g = Integer.toHexString(Random.nextInt(256)).toUpperCase()
        b = Integer.toHexString(Random.nextInt(256)).toUpperCase()

        r = if (r.length == 1) "0$r" else r
        g = if (g.length == 1) "0$g" else g
        b = if (b.length == 1) "0$b" else b

        return r + g + b
    }

    fun randomColor(): Int {
        var r: String
        var g: String
        var b: String
        r = Integer.toHexString(Random.nextInt(256)).toUpperCase()
        g = Integer.toHexString(Random.nextInt(256)).toUpperCase()
        b = Integer.toHexString(Random.nextInt(256)).toUpperCase()

        r = if (r.length == 1) "0$r" else r
        g = if (g.length == 1) "0$g" else g
        b = if (b.length == 1) "0$b" else b

        return Color.parseColor("#${r + g + b}")
    }

    /**
    * 避免不合法字符串转换成int
    */
    fun String?.toSafeInt(defInt:Int=-1): Int {
        if(!this.isNullOrEmpty()&&TextUtils.isDigitsOnly(this)){
            return this!!.toInt()
        }
        return defInt
    }


}