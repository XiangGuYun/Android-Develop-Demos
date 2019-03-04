package com.androidui.draw.paint

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_xfermode.*

@LayoutId(R.layout.activity_xfermode)
class XfermodeActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        val listMode = listOf(
                PorterDuff.Mode.SRC,
                PorterDuff.Mode.SRC_IN,
                PorterDuff.Mode.SRC_OUT,
                PorterDuff.Mode.SRC_ATOP,
                PorterDuff.Mode.SRC_OVER,
                PorterDuff.Mode.DST,
                PorterDuff.Mode.DST_IN,
                PorterDuff.Mode.DST_OUT,
                PorterDuff.Mode.DST_ATOP,
                PorterDuff.Mode.DST_OVER,
                PorterDuff.Mode.XOR,
                PorterDuff.Mode.CLEAR,
                PorterDuff.Mode.ADD,
                PorterDuff.Mode.MULTIPLY,
                PorterDuff.Mode.DARKEN,
                PorterDuff.Mode.LIGHTEN,
                PorterDuff.Mode.OVERLAY,
                PorterDuff.Mode.SCREEN)
        val listName = listOf(
                "SRC",
                "SRC_IN",
                "SRC_OUT",
                "SRC_ATOP",
                "SRC_OVER",
                "DST",
                "DST_IN",
                "DST_OUT",
                "DST_ATOP",
                "DST_OVER",
                "XOR",
                "CLEAR",
                "ADD",
                "MULTIPLY",
                "DARKEN",
                "LIGHTEN",
                "OVERLAY",
                "SCREEN"
        )
        spinner.adapter =ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listName)
        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                study1View.mode = listMode[position]
                study2View.mode = listMode[position]
                study1View.invalidate()
                study2View.invalidate()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        header1.setLeftClick {
            webDialog.url("canvas/xfermode1")
        }

    }
}
