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

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.android.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import com.transitionseverywhere.Fade
import com.transitionseverywhere.TransitionManager
import com.transitionseverywhere.TransitionSet
import com.transitionseverywhere.extra.Scale
import kotlinx.android.synthetic.main.fragment_scale.*

/**
 * Created by Andrey Kulikov on 24/03/16.
 */
@LayoutId(id = R.layout.fragment_scale)
class ScaleSample : KotlinFragment() {

    override fun init() {

        header.setRightClick {
            getAct().codeDialog.text("""
button1.setOnClickListener(object : VisibleToggleClickListener() {

    override fun changeVisibility(visible: Boolean) {
        TransitionManager.beginDelayedTransition(transitions_container, Scale())
        text1.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

})

button2.setOnClickListener(object : VisibleToggleClickListener() {

    override fun changeVisibility(visible: Boolean) {
        val set = TransitionSet()
                .addTransition(Scale(0.7f))
                .addTransition(Fade())
                .setInterpolator(if (visible) LinearOutSlowInInterpolator() else FastOutLinearInInterpolator())
        TransitionManager.beginDelayedTransition(transitions_container, set)
        text2.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

})
            """.trimIndent())
        }

        button1.setOnClickListener(object : VisibleToggleClickListener() {

            override fun changeVisibility(visible: Boolean) {
                TransitionManager.beginDelayedTransition(transitions_container, Scale())
                text1.visibility = if (visible) View.VISIBLE else View.INVISIBLE
            }

        })

        val interpolator1 = AccelerateInterpolator()
        val interpolator2 = DecelerateInterpolator()

        button2.setOnClickListener(object : VisibleToggleClickListener() {

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
            override fun changeVisibility(visible: Boolean) {
                val set = TransitionSet()
                        .addTransition(Scale(0.7f))
                        .addTransition(Fade())
                        .setDuration(1200)
                        .setInterpolator(if (visible) interpolator1 else interpolator2)
                TransitionManager.beginDelayedTransition(transitions_container, set)
                text2.visibility = if (visible) View.VISIBLE else View.INVISIBLE
            }

        })

    }
}
