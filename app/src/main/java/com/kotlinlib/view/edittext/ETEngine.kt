package com.kotlinlib.view.edittext

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText

interface ETEngine {

    class TextWatcherImpl: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }

    class onTextChanged(val func:(CharSequence?)->Unit): TextWatcher by TextWatcherImpl(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            func.invoke(s)
        }
    }

    companion object {
        /**
         * 注意EditText一定要有
            android:inputType="text"
            android:imeOptions="actionSearch"
         */
        fun relSearch(et:EditText, pressSearch:()->Unit){
            et.setOnEditorActionListener { _, id, _ ->
                if (id == EditorInfo.IME_ACTION_SEARCH){
                    pressSearch.invoke()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }



    }



}