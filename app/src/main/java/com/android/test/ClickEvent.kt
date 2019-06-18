package com.android.test

import android.view.View
import com.kotlinlib.other.BaseInterface

interface ClickEvent:BaseInterface {

    fun click1(view:View){
        println(1)
    }

    fun click2(view:View){
        println(2)
    }

}