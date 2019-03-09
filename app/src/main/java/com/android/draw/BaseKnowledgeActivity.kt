package com.android.draw

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.R
import kotlinx.android.synthetic.main.activity_base_knowledge.*

/**
 * 基础知识
 */
class BaseKnowledgeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_knowledge)

        tv1.text = """
            屏幕尺寸信息
1.屏幕参数
一块屏幕通常具备以下的几个参数
屏幕大小：
指_____的长度，通常用寸来表示，例如4.7寸，5.5寸
分辨率：
分辨率是指实际屏幕的_____，例如720X1280就是指屏幕的分辨率，宽有720个像素点，高有1280个像素点
PPI
_____又称为DPI，他是由对角线的的像素点数除以屏幕的大小所得，通常有400PPI就已经很6了
        """.trimIndent()


    }
}
