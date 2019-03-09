package com.android.supportlib.textinput

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.listener.OnTextWatcher
import com.kotlinlib.other.LayoutId
import com.kotlinlib.regex.RegexUtils
import kotlinx.android.synthetic.main.activity_text_input_layout.*

@LayoutId(R.layout.activity_text_input_layout)
class TextInputActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        et2.addTextChangedListener(object : OnTextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!RegexUtils.checkPhoneNum(s.toString())){
                    til2.error = "手机格式错误"
                    til2.isErrorEnabled = true
                }else {
                    til2.isErrorEnabled = false
                }
            }
        })
    }
}
