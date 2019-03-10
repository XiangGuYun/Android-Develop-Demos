package com.android.systemui.popupwindow

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow

open class CommonPopupWindow(var context: Context, var layoutRes: Int, var w: Int, var h: Int) {
    lateinit var contentView: View
    lateinit var popupWindow: PopupWindow

    fun get(initView:(View)->Unit): CommonPopupWindow {
        contentView = LayoutInflater.from(context).inflate(layoutRes, null, false)
        initView.invoke(contentView)
        popupWindow = PopupWindow(contentView, w, h, true)
        initWindow()
        return this
    }

    fun getWindow(): PopupWindow {
        return popupWindow
    }

    fun initWindow() {
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popupWindow.isOutsideTouchable = true
        popupWindow.isTouchable = true
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.WHITE))
    }

    fun showBashOfAnchor(anchor: View, layoutGravity: LayoutGravity, xmerge: Int, ymerge: Int) {
        val offset = layoutGravity.getOffset(anchor, popupWindow)
        popupWindow.showAsDropDown(anchor, offset[0] + xmerge, offset[1] + ymerge)
    }

    fun showAsDropDown(anchor: View, xoff: Int, yoff: Int) {
        popupWindow.showAsDropDown(anchor, xoff, yoff)
    }

    fun showAtLocation(parent: View, gravity: Int, x: Int, y: Int) {
        popupWindow.showAtLocation(parent, gravity, x, y)
    }
}