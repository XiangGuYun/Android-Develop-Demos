package com.androidui.view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.androidui.R
import kotlinx.android.synthetic.main.header_view1.view.*

class MyTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        TextView(context, attrs, defStyleAttr){


    init {
       typeface = Typeface.createFromAsset(context.assets, "font/miaohuntype.ttf")
    }


}