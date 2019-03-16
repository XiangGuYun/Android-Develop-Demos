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

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.android.R
import com.android.common.view.Header1View
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import com.transitionseverywhere.ChangeBounds
import com.transitionseverywhere.ChangeImageTransform
import com.transitionseverywhere.TransitionManager
import com.transitionseverywhere.TransitionSet
import kotlinx.android.synthetic.main.fragment_image_transform.*

/**
 * Created by Andrey Kulikov on 20/03/16.
 */
@LayoutId(R.layout.fragment_image_transform)
class ImageTransformSample : KotlinFragment() {

    override fun init() {

        header.setRightClick {
            getAct().codeDialog.text("""
var mExpanded = false

image.click {
    mExpanded = !mExpanded

    TransitionManager.beginDelayedTransition(transitions_container, TransitionSet()
            .addTransition(ChangeBounds())
            .addTransition(ChangeImageTransform()))

    val params = image.layoutParams
    params.height = if (mExpanded) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
    image.layoutParams = params

    image.scaleType = if (mExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
}
            """.trimIndent())
        }

        var mExpanded = false

        image.click {
            mExpanded = !mExpanded

            TransitionManager.beginDelayedTransition(transitions_container, TransitionSet()
                    .addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform()))

            val params = image.layoutParams
            params.height = if (mExpanded) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
            image.layoutParams = params

            image.scaleType = if (mExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
        }
    }
}
