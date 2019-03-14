package com.android.customview.measure_layout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.android.R

class MyViewGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
ViewGroup(context, attrs, defStyleAttr) {

    private var horizontalSpace = 0
    private var verticalSpace = 0

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.MyViewGroup)
        horizontalSpace = ta.getInt(R.styleable.MyViewGroup_horizontalSpace, 0)
        verticalSpace = ta.getInt(R.styleable.MyViewGroup_verticalSpace, 0)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var hadUsedHorizontal = 0//水平已经使用的距离
        var hadUsedVertical = 0//垂直已经使用的距离
        val width = measuredWidth
        //        int height = getMeasuredHeight();
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            //判断是否已经超出宽度
            if (view.measuredWidth + hadUsedHorizontal > width) {
                //已经超出了宽度
                hadUsedVertical += view.measuredHeight + verticalSpace
                hadUsedHorizontal = 0
            }
            view.layout(hadUsedHorizontal, hadUsedVertical, hadUsedHorizontal + view.measuredWidth, hadUsedVertical + view.measuredHeight)
            hadUsedHorizontal += horizontalSpace + view.measuredWidth
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            measureChild(view, widthMeasureSpec, heightMeasureSpec)
        }
    }

    /**
     * @param child                   子View
     * @param parentWidthMeasureSpec  宽度测量规格
     * @param widthUsed               父view在宽度上已经使用的距离
     * @param parentHeightMeasureSpec 高度测量规格
     * @param heightUsed              父view在高度上已经使用的距离
     */
    override fun measureChildWithMargins(child: View, parentWidthMeasureSpec: Int, widthUsed: Int, parentHeightMeasureSpec: Int, heightUsed: Int) {
        super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed)
    }

}