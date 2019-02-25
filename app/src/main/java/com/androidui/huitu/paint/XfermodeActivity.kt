package com.androidui.huitu.paint

import android.graphics.PorterDuff
import android.graphics.Xfermode
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import com.androidui.R
import kotlinx.android.synthetic.main.activity_xfermode.*

class XfermodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xfermode)

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

    }
}
