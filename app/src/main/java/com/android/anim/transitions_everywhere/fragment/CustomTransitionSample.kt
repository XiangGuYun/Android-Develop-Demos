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

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.ViewGroup
import android.widget.ProgressBar
import com.android.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import com.transitionseverywhere.Transition
import com.transitionseverywhere.TransitionManager
import com.transitionseverywhere.TransitionValues
import com.transitionseverywhere.utils.IntProperty
import kotlinx.android.synthetic.main.fragment_custom_transition.*


/**
 * Created by Andrey Kulikov on 17/04/16.
 */
@LayoutId(id = R.layout.fragment_custom_transition)
class CustomTransitionSample : KotlinFragment() {


    override fun init() {
        header.setRightClick {
            getAct().codeDialog.text("""
button1.setOnClickListener { setProgress(progress_bar.progress - 20) }
button2.setOnClickListener { setProgress(progress_bar.progress + 30) }

private fun setProgress(value: Int) {
    var value = value
    TransitionManager.beginDelayedTransition(transitions_container, ProgressTransition())
    value = Math.max(0, Math.min(100, value))
    progress_bar.progress = value
}

private class ProgressTransition : Transition() {

override fun captureStartValues(transitionValues: TransitionValues) {
    captureValues(transitionValues)
}

override fun captureEndValues(transitionValues: TransitionValues) {
    captureValues(transitionValues)
}

private fun captureValues(transitionValues: TransitionValues) {
    if (transitionValues.view is ProgressBar) {
        // save current progress in the values map
        val progressBar = transitionValues.view as ProgressBar
        transitionValues.values[PROPNAME_PROGRESS] = progressBar.progress
    }
}

override fun createAnimator(sceneRoot: ViewGroup?, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {
    if (startValues != null && endValues != null && endValues.view is ProgressBar) {
        val progressBar = endValues.view as ProgressBar
        val start = (startValues.values[PROPNAME_PROGRESS] as Int?)!!
        val end = (endValues.values[PROPNAME_PROGRESS] as Int?)!!
        if (start != end) {
            // first of all we need to return the start value, because right now
            // the view is have the end value
            progressBar.progress = start
            // create animator with our progressBar, property and end value
            return ObjectAnimator.ofInt(progressBar, PROGRESS_PROPERTY, end)
        }
    }
    return null
}

companion object {

    /**
     * Property is like a helper that contain setter and getter in one place
     */
    private val PROGRESS_PROPERTY = object : IntProperty<ProgressBar>() {

        override fun setValue(progressBar: ProgressBar, value: Int) {
            progressBar.progress = value
        }

        override fun get(progressBar: ProgressBar): Int {
            return progressBar.progress
        }
    }

    /**
     * Internal name of property. Like a bundles for intent
     */
    private val PROPNAME_PROGRESS = "ProgressTransition:progress"
}
            """.trimIndent())
        }
        button1.setOnClickListener { setProgress(progress_bar.progress - 20) }
        button2.setOnClickListener { setProgress(progress_bar.progress + 30) }
    }

    private fun setProgress(value: Int) {
        var value = value
        TransitionManager.beginDelayedTransition(transitions_container, ProgressTransition())
        value = Math.max(0, Math.min(100, value))
        progress_bar.progress = value
    }

    private class ProgressTransition : Transition() {

        override fun captureStartValues(transitionValues: TransitionValues) {
            captureValues(transitionValues)
        }

        override fun captureEndValues(transitionValues: TransitionValues) {
            captureValues(transitionValues)
        }

        private fun captureValues(transitionValues: TransitionValues) {
            if (transitionValues.view is ProgressBar) {
                // save current progress in the values map
                val progressBar = transitionValues.view as ProgressBar
                transitionValues.values[PROPNAME_PROGRESS] = progressBar.progress
            }
        }

        override fun createAnimator(sceneRoot: ViewGroup?, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {
            if (startValues != null && endValues != null && endValues.view is ProgressBar) {
                val progressBar = endValues.view as ProgressBar
                val start = (startValues.values[PROPNAME_PROGRESS] as Int?)!!
                val end = (endValues.values[PROPNAME_PROGRESS] as Int?)!!
                if (start != end) {
                    // first of all we need to return the start value, because right now
                    // the view is have the end value
                    progressBar.progress = start
                    // create animator with our progressBar, property and end value
                    return ObjectAnimator.ofInt(progressBar, PROGRESS_PROPERTY, end)
                }
            }
            return null
        }

        companion object {

            /**
             * Property is like a helper that contain setter and getter in one place
             */
            private val PROGRESS_PROPERTY = object : IntProperty<ProgressBar>() {

                override fun setValue(progressBar: ProgressBar, value: Int) {
                    progressBar.progress = value
                }

                override fun get(progressBar: ProgressBar): Int {
                    return progressBar.progress
                }
            }

            /**
             * Internal name of property. Like a bundles for intent
             */
            private val PROPNAME_PROGRESS = "ProgressTransition:progress"
        }
    }

}
