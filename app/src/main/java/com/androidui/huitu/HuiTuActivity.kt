package com.androidui.huitu

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidui.R
import com.kotlinlib.view.recyclerview.RVInterface
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_hui_tu.*

class HuiTuActivity : AppCompatActivity(),RVInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hui_tu)

        btnBaseKnowledge.setOnClickListener {
            startActivity(Intent(this,BaseKnowledgeActivity::class.java))
        }
        /*
        画笔
         */
        val listPaint = listOf("STYLE","CAP","JOIN","ALIGN","XFERMODE")
        RVUtils(rvPaint).rvAdapter(listPaint,{
            holder, pos ->
            holder.tv(R.id.tvTitle).text = listPaint[pos]
        },R.layout.item_catalog)
        btnPaint.setOnClickListener {
            startActivity(Intent(this,PaintActivity::class.java))
        }
    }
}
