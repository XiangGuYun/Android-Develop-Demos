package com.android

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import org.junit.Test

class ExampleUnitTest {




    @Test
    fun test() {

    }


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


}
