package com.kotlinlib.view.textview

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import com.kotlinlib.other.StringUtils
import kotlinx.android.synthetic.main.activity_new_path.*

/**
 * 文本工具类
 * @property textString String
 */
interface TextViewUtils:StringUtils {
    //返回文字内容
    val TextView.textString: String get()=text.toString()

    /**
     * 设置部分点击文本(仅限一部分)
     */
    fun TextView.setClickText(txt: String, start:Int, end:Int, callback:()->Unit){
        val ss = SpannableString(txt)
        ss.setSpan(object :ClickableSpan(){
            override fun onClick(widget: View) {
                callback.invoke()
            }
        },start,end,Spanned.SPAN_COMPOSING)
        text = ss
        movementMethod = LinkMovementMethod.getInstance()
    }

    /**
     * 设置文本
     */
    fun <T:TextView> T.text(text:String): T {
        this.text = text
        return this
    }

    /**
     * 设置提示
     */
    fun <T:TextView> T.hint(text:String):T{
        this.hint = text
        return this
    }

    /**
     * 设置颜色
     */
    fun <T:TextView> T.color(color:Int): T {
        setTextColor(color)
        return this
    }

    /**
     * 设置颜色
     */
    fun <T:TextView> T.color(color:String): T {
        setTextColor(Color.parseColor(color))
        return this
    }

    /**
     * 设置字体大小
     */
    fun <T:TextView> T.size(size:Int): T {
        textSize = size.toFloat()
        return this
    }

    /**
     * 设置HTML格式文本
     */
    fun <T:TextView> T.html(text:String):T{
        setText(Html.fromHtml(text))
        return this
    }

    /**
     * 获取TextView的Drawable
     */
    fun Context.tvDrawable(id:Int): Drawable {
        val drawable = resources.getDrawable(id)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        return drawable
    }

    /**
     * 设置左边图片
     */
    fun TextView.setLeftTVDrawable(id:Int){
        this.setCompoundDrawables(context.tvDrawable(id),null,null,null)
    }

    /**
     * 设置右边图片
     */
    fun TextView.setRightTVDrawable(id:Int){
        this.setCompoundDrawables(null,null,context.tvDrawable(id),null)
    }

    /**
     * 设置上边图片
     */
    fun TextView.setTopTVDrawable(id:Int){
        this.setCompoundDrawables(null,context.tvDrawable(id),null,null)
    }

    /**
     * 设置下边图片
     */
    fun TextView.setBtmTVDrawable(id:Int){
        this.setCompoundDrawables(null,null,null,context.tvDrawable(id))
    }

    /**
     * 取消设置图片
     */
    fun TextView.setNullTVDrawable(){
        this.setCompoundDrawables(null,null,null,null)
    }

    /**
     * 获取TextView的文本宽度(带间隙)
     */
    fun TextView.getTextWidth(): Float {
        val paint = Paint()
        paint.textSize = this.textSize
        return paint.measureText(textString,0,textString.length)
    }

    /**
     * 获取字符串的文本宽度(带间隙)
     */
    fun String.getTextWidth(textSize:Float): Float {
        val paint = Paint()
        paint.textSize = textSize
        return paint.measureText(this,0,this.length)
    }

    /**
     * 获取文本宽度(不带间隙)
     */
    fun TextView.getPureTextWidth(): Int {
        val rect = Rect()
        val paint = Paint()
        paint.textSize = textSize
        paint.getTextBounds(textString, 0, textString.length, rect)
        return rect.width()
    }

    /**
     * 获取字符串的宽度(不带间隙)
     */
    fun String.getPureTextWidth(textSize:Float): Int {
        val rect = Rect()
        val paint = Paint()
        paint.textSize = textSize
        paint.getTextBounds(this, 0, this.length, rect)
        return rect.width()
    }


}