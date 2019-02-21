package com.androidui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.androidui.R
import kotlinx.android.synthetic.main.read_more.view.*

class ReadMoreView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr){

    private var view: View

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ReadMoreView)
        view = LayoutInflater.from(context).inflate(R.layout.read_more, null)
        view.tvTop.text = ta.getString(R.styleable.ReadMoreView_text)
        view.tvShowAll.setOnClickListener {
            if(view.tvTop.maxLines==5){
                view.tvTop.maxLines = 1000
                view.tvShowAll.text = "收起"
            } else{
                view.tvTop.maxLines = 5
                view.tvShowAll.text = "显示全文"
            }

        }
        ta.recycle()
        addView(view)
    }

    fun setText(text:String){
        view.tvTop.text = text
    }


}