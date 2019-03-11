package com.android.customview.measure_layout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.kotlinlib.other.BaseInterface

class WrapView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr), BaseInterface{

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 获取合适的宽度值
        val width = getProperSize(widthMeasureSpec)
        // 获取合适的高度值
        val height = getProperSize( heightMeasureSpec)
        // 设置宽高尺寸大小值，此方法决定view最终的尺寸大小
        setMeasuredDimension(width, height)

    }

    /**
     * 获取合适的大小
     * @author leibing
     * @createTime 2016/09/19
     * @lastModify 2016/09/19
     * @param defaultSize 默认大小
     * @param measureSpec 测量规格
     * @return
     */
    private fun getProperSize( measureSpec: Int): Int {
        val mode = View.MeasureSpec.getMode(measureSpec)
        val size = View.MeasureSpec.getSize(measureSpec)
        return when (mode) {
            View.MeasureSpec.EXACTLY ->
                // 固定大小，无需更改其大小
                size
           else ->
                // 此处该值可以取小于等于最大值的任意值，此处取屏幕宽度的1/2
                context.srnWidth/4
        }
    }

}