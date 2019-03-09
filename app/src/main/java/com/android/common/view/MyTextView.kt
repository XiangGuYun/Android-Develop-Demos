package com.android.common.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

class MyTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        TextView(context, attrs, defStyleAttr){


    init {
       typeface = Typeface.createFromAsset(context.assets, "font/miaohuntype.ttf")
    }


}