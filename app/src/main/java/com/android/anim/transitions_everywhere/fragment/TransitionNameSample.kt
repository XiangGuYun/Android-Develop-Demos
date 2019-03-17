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

import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.android.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import com.transitionseverywhere.ChangeBounds
import com.transitionseverywhere.TransitionManager
import kotlinx.android.synthetic.main.fragment_names.*
import java.util.*

/**
 * Created by Andrey Kulikov on 12/05/16.
 */
@LayoutId(R.layout.fragment_names)
class TransitionNameSample : KotlinFragment() {

    /**
     * @param layout ViewGroup Fragment父容器
     * @param titles List<String> 文字列表
     */
    private fun createViews(layout: ViewGroup, titles: List<String>) {
        layout.removeAllViews()
        for (title in titles) {
            val textView = LayoutInflater.from(getAct()).inflate(R.layout.fragment_names_item, layout, false) as TextView
            textView.text = title
            ViewCompat.setTransitionName(textView, title)
            layout.addView(textView)
        }
    }


    override fun init() {

        val titles = ArrayList<String>()

        for (i in 0..4) {
            titles.add(String.format("Item %d", i + 1))
        }

        createViews(transitions_container, titles)

        button1.click { v ->
            TransitionManager.beginDelayedTransition(transitions_container, ChangeBounds())
            titles.shuffle()
            createViews(transitions_container, titles)
        }

        showCode()

    }

    private fun showCode() {
        header.setRightClick {
            getAct().codeDialog.text("""
val titles = ArrayList<String>()

for (i in 0..4) {
    titles.add(String.format("Item %d", i + 1))
}

createViews(transitions_container, titles)

button1.click { v ->
    TransitionManager.beginDelayedTransition(transitions_container, ChangeBounds())
    titles.shuffle()
    createViews(transitions_container, titles)
}

fun createViews(layout: ViewGroup, titles: List<String>) {
    layout.removeAllViews()
    for (title in titles) {
        val textView = LayoutInflater.from(getAct()).inflate(R.layout.fragment_names_item, layout, false) as TextView
        textView.text = title
        ViewCompat.setTransitionName(textView, title)
        layout.addView(textView)
    }
}
            """.trimIndent())
        }
    }
}
