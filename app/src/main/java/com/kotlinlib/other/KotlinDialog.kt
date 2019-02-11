package com.kotlinlib.other

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.TextView
import com.kotlinlib.dialog.DialogInfo
import com.kotlinlib.view.base.ViewUtils
import com.kotlinlib.view.textview.TextViewUtils

open class KotlinDialog(ctx:Context):Dialog(ctx),ViewUtils,TextViewUtils,StringUtils {

    var dv: View

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val dialogInject = this::class.annotations[0] as DialogInfo
        dv = LayoutInflater.from(context).inflate(dialogInject.layoutId, null)
        setContentView(dv)
        val dialogWindow = window
        dialogWindow!!.setBackgroundDrawableResource(android.R.color.transparent)
        val lp = dialogWindow.attributes
        lp.width = DensityUtils.dip2px(ctx,dialogInject.width.toFloat())
        lp.height = DensityUtils.dip2px(ctx,dialogInject.height.toFloat())
        dialogWindow.attributes = lp
    }


}