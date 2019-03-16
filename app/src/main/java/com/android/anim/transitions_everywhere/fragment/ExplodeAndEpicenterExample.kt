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

import com.android.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.fragment_explore_and_epicenter.*
import android.graphics.Rect
import android.view.View
import com.transitionseverywhere.*


/**
 * Created by Andrey Kulikov on 25/03/16.
 */
@LayoutId(R.layout.fragment_explore_and_epicenter)
class ExplodeAndEpicenterExample : KotlinFragment() {

    override fun init() {

        header.setRightClick {
            getAct().codeDialog.text("""
RVUtils(rvTest).gridManager(4,true)
            .rvAdapter((1..20).toList(),
                    {
                        holder, pos ->
                        holder.itemClick {
                            letsExplodeIt(holder.getItemView())
                        }
                    }, R.layout.explode_item)

private fun letsExplodeIt(clickedView: View) {
    // save rect of view in screen coordinated
    val viewRect = Rect()
    clickedView.getGlobalVisibleRect(viewRect)

    val explode = Explode()
    explode.epicenterCallback = object : Transition.EpicenterCallback() {
        override fun onGetEpicenter(transition: Transition): Rect {
            return viewRect
        }
    }
    explode.excludeTarget(clickedView, true)
    val set = TransitionSet()
            .addTransition(explode)
            .addTransition(Fade().addTarget(clickedView))
            .addListener(object : Transition.TransitionListenerAdapter() {
                override fun onTransitionEnd(transition: Transition) {
                    transition.removeListener(this)
                    activity!!.onBackPressed()
                }
            })
    TransitionManager.beginDelayedTransition(rvTest, set)

    // remove all views from Recycler View
    rvTest.adapter = null
}
            """.trimIndent())
        }

        RVUtils(rvTest).gridManager(4,true)
                .rvAdapter((1..20).toList(),
                        {
                            holder, pos ->
                            holder.itemClick {
                                letsExplodeIt(holder.getItemView())
                            }
                        }, R.layout.explode_item)
    }

    private fun letsExplodeIt(clickedView: View) {
        // save rect of view in screen coordinated
        val viewRect = Rect()
        clickedView.getGlobalVisibleRect(viewRect)

        val explode = Explode()
        explode.epicenterCallback = object : Transition.EpicenterCallback() {
            override fun onGetEpicenter(transition: Transition): Rect {
                return viewRect
            }
        }
        explode.excludeTarget(clickedView, true)
        val set = TransitionSet()
                .addTransition(explode)
                .addTransition(Fade().addTarget(clickedView))
                .addListener(object : Transition.TransitionListenerAdapter() {
                    override fun onTransitionEnd(transition: Transition) {
                        transition.removeListener(this)
                        activity!!.onBackPressed()
                    }
                })
        TransitionManager.beginDelayedTransition(rvTest, set)

        // remove all views from Recycler View
        rvTest.adapter = null
    }

}
