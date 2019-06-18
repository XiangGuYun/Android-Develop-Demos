package com.android.test

import android.text.TextWatcher
import com.kotlinlib.listener.OnTextWatcher

interface TextWatcher {

    val watcher1:TextWatcher
        get() = object : OnTextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                println(s)
            }
        }

    val watcher2:TextWatcher
        get() = object : OnTextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                println(s)
            }
        }

}