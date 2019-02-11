package com.kotlinlib.view.edittext

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import com.kotlinlib.view.keyboard.SoftKeyBoardListener

class KotlinEditText  @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        EditText(context, attrs,defStyleAttr) {
        init {
            isCursorVisible = false
            SoftKeyBoardListener(context as Activity).setOnSoftKeyBoardChangeListener(
                    object : SoftKeyBoardListener.OnSoftKeyBoardChangeListener{
                        override fun keyBoardShow(height: Int) {
                            isCursorVisible = true
                        }

                        override fun keyBoardHide(height: Int) {
                            isCursorVisible = false
                        }
                    })
        }
}