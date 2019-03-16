/*
 * Copyright (C) 2016 Andrey Kulikov (andkulikov@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.anim.transitions_everywhere.fragment

import android.view.View
import com.android.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import com.transitionseverywhere.ChangeText
import com.transitionseverywhere.TransitionManager
import kotlinx.android.synthetic.main.fragment_change_text.*

/**
 * Created by Andrey Kulikov on 17/04/16.
 */
@LayoutId(R.layout.fragment_change_text)
class ChangeTextSample : KotlinFragment() {

    override fun init() {

        header.setRightClick {
            getAct().codeDialog.text("""
companion object {
    val TEXT_1 = "Hi, i am text. Tap on me!"
    val TEXT_2 = "Thanks! Another tap?"
}

tv.text = TEXT_1
tv.setOnClickListener(object : View.OnClickListener {

    internal var mSecondText: Boolean = false

    override fun onClick(v: View) {
        mSecondText = !mSecondText
        TransitionManager.beginDelayedTransition(transitions_container,
                ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN))
        tv.text = if (mSecondText) TEXT_2 else TEXT_1
    }

})
            """.trimIndent())
        }

        tv.text = TEXT_1
        tv.setOnClickListener(object : View.OnClickListener {

            internal var mSecondText: Boolean = false

            override fun onClick(v: View) {
                mSecondText = !mSecondText
                TransitionManager.beginDelayedTransition(transitions_container,
                        ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN))
                tv.text = if (mSecondText) TEXT_2 else TEXT_1
            }

        })
    }

    companion object {
        val TEXT_1 = "Hi, i am text. Tap on me!"
        val TEXT_2 = "Thanks! Another tap?"
    }
}
