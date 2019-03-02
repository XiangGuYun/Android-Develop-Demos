package com.kotlinlib.view.recyclerview

import android.support.v7.widget.*
import android.support.v7.widget.DividerItemDecoration
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
     * 设置带有HeaderView的适配器
     * @receiver RVUtils
     * @param data List<T>
     * @param headerViewId Int
     * @param handleHeaderView (holder:Holder)->Unit
     * @param handleNormalView (holder:Holder,pos:Int)->Unit
     * @param handleNormalLayoutIndex (pos:Int)->Int
     * @param itemId IntArray
     */
    fun <T> RVUtils.rvAdapterH(data:List<T>,
                                     headerViewId:Int,
                                     handleHeaderView:(holder:Holder)->Unit,
                                     handleNormalView:(holder:Holder,pos:Int)->Unit,
                                     handleNormalLayoutIndex:(pos:Int)->Int,
                                     vararg itemId:Int){
        needHeader = true
        rvMultiAdapter(data, {
            holder, pos ->
            when(pos){
                0->{handleHeaderView.invoke(holder)}
                else->{handleNormalView.invoke(holder, pos)}
            }
        },{
            when(it){
                0->0
                else->handleNormalLayoutIndex.invoke(it)+1
            }
        }, headerViewId, *itemId)
    }

    /**
     * 设置带有HeaderView和FooterView的适配器
     * @receiver RVUtils
     * @param data List<T>
     * @param headerViewId Int
     * @param handleHeaderView (holder:Holder)->Unit
     * @param handleNormalView (holder:Holder,pos:Int)->Unit
     * @param handleNormalLayoutIndex (pos:Int)->Int
     * @param itemId IntArray
     */
    fun <T> RVUtils.rvAdapterHF(data:List<T>,
                                    headerViewId:Int,
                                    handleHeaderView:(headerHolder:Holder)->Unit,
                                    footerViewId:Int,
                                    handleFooterView:(footerHolder:Holder)->Unit,
                                    handleNormalView:(normalHolder:Holder,pos:Int)->Unit,
                                    handleNormalLayoutIndex:(pos:Int)->Int,
                                    vararg itemId:Int): RVUtils {
        needHeader = true
        needFooter = true
        rvMultiAdapter(data, {
            holder, pos ->
            when(pos){
                0->{handleHeaderView.invoke(holder)}
                data.lastIndex->{handleFooterView.invoke(holder)}
                else->{
                    handleNormalView.invoke(holder, pos)
                }
            }
        },{
            when(it){
                0->0
                data.lastIndex->1
                else->handleNormalLayoutIndex.invoke(it)+2
            }
        }, headerViewId, footerViewId, *itemId)
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

    fun Holder.ir(ivId:Int, imgId:Int): Holder{
        setImageResource(ivId, imgId)
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


    fun Holder.textColor(id:Int, color:Int):Holder{
        setTextColor(id, color)
        return this
    }

    fun Holder.itemClick(click:(view: View)->Unit): Holder {
        setOnItemViewClickListener(click)
        return this
    }

    fun Holder.itemLongClick(click:(view: View)->Unit){
        getItemView().setOnLongClickListener{
            click.invoke(it)
            return@setOnLongClickListener true
        }
    }

    fun Holder.htmlText(id:Int, html:String){
        getView<TextView>(id).text = Html.fromHtml(html)
    }

    /**
     * 添加分割线
     * @receiver RVUtils
     * @param drawableId Int
     * @param isVertical Boolean
     * @return RVUtils
     */
    fun RVUtils.decorate(drawableId:Int, isVertical:Boolean=true): RVUtils {
        val divider = DividerItemDecoration(context, if(isVertical)DividerItemDecoration.VERTICAL else DividerItemDecoration.HORIZONTAL)
        divider.setDrawable(context.resources.getDrawable(drawableId))
        rv.addItemDecoration(divider)
        return this
    }

    fun RVUtils.decorate(isVertical:Boolean=true): RVUtils {
        rv.addItemDecoration(DividerItemDecoration(context, if(isVertical)DividerItemDecoration.VERTICAL else DividerItemDecoration.HORIZONTAL))
        return this
    }

    fun RVUtils.decorate(decoration: RecyclerView.ItemDecoration): RVUtils {
        rv.addItemDecoration(decoration)
        return this
    }

    /**
     * 设置吸附
     * @receiver RVUtils
     * @return RVUtils
     */
    fun RVUtils.snapLinear(): RVUtils {
        val helper = LinearSnapHelper()
        helper.attachToRecyclerView(rv)
        return this
    }

    /**
     * 设置吸附
     * @receiver RVUtils
     * @return RVUtils
     */
    fun RVUtils.snapPager(): RVUtils {
        val helper = PagerSnapHelper()
        helper.attachToRecyclerView(rv)
        return this
    }

    /**
     * 设置吸附
     * @receiver RVUtils
     * @return RVUtils
     */
    fun RVUtils.customSnap(set:(rv:RecyclerView)->Unit): RVUtils {
        set.invoke(rv)
        return this
    }

    fun RVUtils.customSnap(snapHelper: SnapHelper): RVUtils {
        snapHelper.attachToRecyclerView(rv)
        return this
    }

    /**
     * 设置增删动画
     *
Cool
LandingAnimator

Scale
ScaleInAnimator, ScaleInTopAnimator, ScaleInBottomAnimator
ScaleInLeftAnimator, ScaleInRightAnimator

Fade
FadeInAnimator, FadeInDownAnimator, FadeInUpAnimator
FadeInLeftAnimator, FadeInRightAnimator

Flip
FlipInTopXAnimator, FlipInBottomXAnimator
FlipInLeftYAnimator, FlipInRightYAnimator

Slide
SlideInLeftAnimator, SlideInRightAnimator, OvershootInLeftAnimator, OvershootInRightAnimator
SlideInUpAnimator, SlideInDownAnimator
     * @receiver RVUtils
     * @param anim T?
     * @return RVUtils
     */
    fun <T:RecyclerView.ItemAnimator> RVUtils.anim(anim:T?): RVUtils{
        if(anim==null){
            rv.itemAnimator = DefaultItemAnimator()
        } else{
            rv.itemAnimator = anim
        }
        return this
    }

    /**
     * 滚动到指定位置，指定位置会完整地出现在屏幕的最下方
     * @receiver RecyclerView
     * @param position Int
     * @param list List<T>
     */
    fun <T> RecyclerView.scrollTo(position:Int, list:List<T>){
        if (position >= 0 && position <= list.size - 1) {
            val firstItem = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            val lastItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            if (position <= firstItem) {
                scrollToPosition(position)
            } else if (position <= lastItem) {
                val top = getChildAt(position - firstItem).top
                scrollBy(0, top)
            } else {
                scrollToPosition(position)
            }
        }
    }

}