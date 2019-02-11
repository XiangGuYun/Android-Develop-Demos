package com.kotlinlib.view.edittext

import android.text.InputFilter
import android.view.inputmethod.EditorInfo
import android.widget.EditText

class ETEngine {

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