package com.android.test

import android.os.Bundle
import android.text.Editable
import com.android.R
import com.kotlinlib.activity.AbstractKotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_test2.*

@LayoutId(R.layout.activity_test2)
class TestActivity : AbstractKotlinActivity(), ClickEvent, TextWatcher {



    override fun init(bundle: Bundle?) {
        btn1.click(::click1)
        btn2.click(::click2)
        et1.addTextChangedListener(watcher1)
        et2.addTextChangedListener(watcher2)
//        et.addTextChangedListener(object :TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//                "改变了".toast()
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//
//            }
//        })
//
//        et.addTextChangedListener(object :OnTextWatcher{
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                "改变了".toast()
//            }
//        })

    }


}
