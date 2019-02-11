package com.kotlinlib.view.window

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow

/**
 * Created by Administrator on 2018/4/7 0007.
 */

class PopupUtils(ctx: Context, 布局:Int, 宽高:Pair<Int,Int>, 消失事件: ()->Unit,背景色:String="#00ffffff",
                 获得焦点:Boolean=true,外界可触摸:Boolean=false) {

    var window: PopupWindow
    var windowView: View = LayoutInflater.from(ctx).inflate(布局, null)

    init {
        window = PopupWindow(windowView, 宽高.first, 宽高.second)
        window.isFocusable = 获得焦点
        window.setBackgroundDrawable(ColorDrawable(Color.parseColor(背景色)))
        window.isOutsideTouchable = 外界可触摸
        window.update()
        window.setOnDismissListener(消失事件)
    }

    fun dismiss() {
        window.dismiss()
    }


}
