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

class PopupUtils(ctx: Context, layoutId:Int, metric:Pair<Int,Int>, onDismiss: ()->Unit,bgcolor:String="#00ffffff",
                 getFocus:Boolean=true,isOutsideTouchable:Boolean=false) {

    var window: PopupWindow
    var windowView: View = LayoutInflater.from(ctx).inflate(layoutId, null)

    init {
        window = PopupWindow(windowView, metric.first, metric.second)
        window.isFocusable = getFocus
        window.setBackgroundDrawable(ColorDrawable(Color.parseColor(bgcolor)))
        window.isOutsideTouchable = isOutsideTouchable
        window.update()
        window.setOnDismissListener(onDismiss)
    }

    fun dismiss() {
        window.dismiss()
    }


}
