package com.kotlinlib.view.recyclerview

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.kotlinlib.Holder
import com.kotlinlib.other.StringUtils

interface RVInterface:StringUtils {
    /**
     * 设置适配器
     * @receiver RVUtils
     * @param data ArrayList<T> 数据集合
     * @param fun1 (holder: EasyRVHolder, pos:Int)->Unit 绑定数据
     * @param itemId Int 列表项ID
     * @return RVUtils
     */
    fun <T> RVUtils.rvAdapter(data:ArrayList<T>?,
                              fun1:(holder: Holder, pos:Int)->Unit,
                              itemId:Int): RVUtils {
        adapter(data, RVUtils.onBindData(fun1),
                RVUtils.SetMultiCellView { 0 },itemId)
        return this
    }

    fun <T> RVUtils.rvAdapter(data:List<T>?,
                              fun1:(holder: Holder, pos:Int)->Unit,
                              itemId:Int): RVUtils {
        adapter(data, RVUtils.onBindData(fun1),
                RVUtils.SetMultiCellView { 0 },itemId)
        return this
    }


    /**
     * 遍历RecyclerView的子视图
     * @receiver RecyclerView
     * @param fun1 (i:Int,it:View)->Unit
     */
    fun RecyclerView.foreachIndexed(fun1:(i:Int,it:View)->Unit){
        for (i in 0 until childCount){
            fun1.invoke(i,getChildAt(i))
        }
    }

    fun RecyclerView.foreach(fun1:(it:View)->Unit){
        for (i in 0 until childCount){
            fun1.invoke(getChildAt(i))
        }
    }

    /**
     * 设置多个列表项布局的适配器
     * @receiver RVUtils
     * @param data ArrayList<T>
     * @param fun1 (holder: com.kotlinlib.Holder, pos:Int)->Unit
     * @param fun2 (Int)->Int
     * @param itemId IntArray 传入可变长度的ID数组
     * @return RVUtils
     */
    fun <T> RVUtils.rvMultiAdapter(data:ArrayList<T>,
                                   fun1:(holder: Holder, pos:Int)->Unit,
                                   fun2:(pos:Int)->Int,
                                   vararg itemId:Int): RVUtils {
        adapter(data, RVUtils.onBindData(fun1),
                RVUtils.SetMultiCellView(fun2),*itemId)
        return this
    }

    fun <T> RVUtils.rvMultiAdapter(data:List<T>,
                                   fun1:(holder: Holder, pos:Int)->Unit,
                                   fun2:(pos:Int)->Int,
                                   vararg itemId:Int): RVUtils {
        adapter(data, RVUtils.onBindData(fun1),
                RVUtils.SetMultiCellView(fun2),*itemId)
        return this
    }

    /**
     * 简化ViewHolder的view获取
     */
    fun Holder.v(id:Int): View {
        return getView<View>(id)
    }

    fun <T:View> View.view(id:Int): T {
        return findViewById(id)
    }

    fun <T:View> Holder.view(id:Int): T {
        return getView(id)
    }

    fun Holder.iv(id:Int): ImageView {
        return getView(id)
    }

    fun Holder.tv(id:Int): TextView {
        return getView(id)
    }

    fun ImageView.ir(imgId:Int): ImageView{
        setImageResource(imgId)
        return this
    }

    fun Holder.text(id:Int, text:String?):Holder{
        if(text.isNullOrEmpty()){
            setText(id, "")
        }else{
            setText(id, text)
        }

        return this
    }

    fun Holder.text_(id:Int, text:String?):Holder{
        if(text.isNullOrEmpty()){
            setText(id, "-")
        }else{
            setText(id, text)
        }

        return this
    }

    fun Holder.text_phone(id:Int, text:String?):Holder{
        if(text.isNullOrEmpty()){
            setText(id, "-")
        }else{
            setText(id, text?.encryptPhone())
        }

        return this
    }

    fun Holder.textColor(id:Int, color:Int):Holder{
        setTextColor(id, color)
        return this
    }

    fun Holder.itemClick(click:(view: View)->Unit){
        setOnItemViewClickListener(click)
    }

    fun Holder.htmlText(id:Int, html:String){
        getView<TextView>(id).text = Html.fromHtml(html)
    }

}