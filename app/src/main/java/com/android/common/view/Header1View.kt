package com.android.common.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.android.R
import kotlinx.android.synthetic.main.header_view1.view.*

class Header1View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr){

    var subTitle:TextView
    var parent:View

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.Header1View)
        parent = LayoutInflater.from(context).inflate(R.layout.header_view1, null)
        subTitle = parent.tvSubTitle
        parent.tvMainTitle.text = ta.getString(R.styleable.Header1View_mainTitle1)
        parent.tvMainTitle.setTextColor(Color.parseColor("#ffff66"))
        ta.recycle()
        addView(parent)
    }

    fun setLeftClick(func:()->Unit): Header1View {
        parent.tvLeftTitle.visibility = View.VISIBLE
        parent.tvLeftTitle.setOnClickListener {
            func.invoke()
        }
        return this
    }

    fun setRightClick(func: () -> Unit){
        subTitle.visibility = View.VISIBLE
        subTitle.setOnClickListener {
            func.invoke()
        }
    }

}