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

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.android.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import com.transitionseverywhere.Recolor
import com.transitionseverywhere.TransitionManager
import kotlinx.android.synthetic.main.fragment_recolor.*

/**
 * Created by Andrey Kulikov on 17/04/16.
 */
@LayoutId(R.layout.fragment_recolor)
class RecolorSample : KotlinFragment() {

    override fun init() {

        header.setRightClick {
            getAct().codeDialog.text("""
var mColorsInverted = false

button.click {
    TransitionManager.beginDelayedTransition(transitions_container, Recolor())

    mColorsInverted = !mColorsInverted
    button.setTextColor(resources.getColor(if (!mColorsInverted) R.color.second_accent else R.color.accent))
    button.setBackgroundDrawable(
            ColorDrawable(resources.getColor(if (!mColorsInverted) R.color.accent else R.color.second_accent)))
}
            """.trimIndent())
        }

        var mColorsInverted = false

        button.click {
            TransitionManager.beginDelayedTransition(transitions_container, Recolor())

            mColorsInverted = !mColorsInverted
            button.setTextColor(resources.getColor(if (!mColorsInverted) R.color.second_accent else R.color.accent))
            button.setBackgroundDrawable(
                    ColorDrawable(resources.getColor(if (!mColorsInverted) R.color.accent else R.color.second_accent)))
        }
    }
}
