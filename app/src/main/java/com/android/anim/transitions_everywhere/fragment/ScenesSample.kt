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

import android.view.Gravity
import com.android.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import com.transitionseverywhere.*
import kotlinx.android.synthetic.main.fragment_scenes.*
import kotlinx.android.synthetic.main.scene1.*

/**
 * Created by Andrey Kulikov on 20/03/16.
 */
@LayoutId(R.layout.fragment_scenes)
class ScenesSample : KotlinFragment(){

    override fun init() {
        // A Scene can be instantiated from a live view hierarchy.
        val mScene1 = Scene(scene_root, container)

        // You can also inflate a generate a Scene from a layout resource file.
        val mScene2 = Scene.getSceneForLayout(scene_root, R.layout.scene2, context)

        // Another scene from a layout resource file.
        val mScene3 = Scene.getSceneForLayout(scene_root, R.layout.scene3, context)

        // We create a custom TransitionManager for Scene 3, in which ChangeBounds, Fade and
        // ChangeImageTransform take place at the same time.
        val mTransitionManagerForScene3 = TransitionInflater.from(context)
                .inflateTransitionManager(R.transition.scene3_transition_manager, scene_root)

        select_scene.check {
            when (it) {
                R.id.select_scene_1 -> {
                    // You can start an automatic transition with TransitionManager.go().
                    TransitionManager.go(mScene1!!)
                }
                R.id.select_scene_2 -> {
                    val set = TransitionSet()
                    val slide = Slide(Gravity.LEFT)
                    slide.addTarget(R.id.transition_title)
                    set.addTransition(slide)
                    set.addTransition(ChangeBounds())
                    set.ordering = TransitionSet.ORDERING_TOGETHER
                    set.duration = 350
                    TransitionManager.go(mScene2!!, set)
                }
                R.id.select_scene_3 -> {
                    // You can also start a transition with a custom TransitionManager.
                    mTransitionManagerForScene3!!.transitionTo(mScene3!!)
                }
            }
        }

        showCode()
    }

    private fun showCode() {
        header.setRightClick {
            getAct().codeDialog.text("""
// A Scene can be instantiated from a live view hierarchy.
val mScene1 = Scene(scene_root, container)

// You can also inflate a generate a Scene from a layout resource file.
val mScene2 = Scene.getSceneForLayout(scene_root, R.layout.scene2, context)

// Another scene from a layout resource file.
val mScene3 = Scene.getSceneForLayout(scene_root, R.layout.scene3, context)

// We create a custom TransitionManager for Scene 3, in which ChangeBounds, Fade and
// ChangeImageTransform take place at the same time.
val mTransitionManagerForScene3 = TransitionInflater.from(context)
        .inflateTransitionManager(R.transition.scene3_transition_manager, scene_root)

select_scene.check {
    when (it) {
        R.id.select_scene_1 -> {
            // You can start an automatic transition with TransitionManager.go().
            TransitionManager.go(mScene1!!)
        }
        R.id.select_scene_2 -> {
            val set = TransitionSet()
            val slide = Slide(Gravity.LEFT)
            slide.addTarget(R.id.transition_title)
            set.addTransition(slide)
            set.addTransition(ChangeBounds())
            set.ordering = TransitionSet.ORDERING_TOGETHER
            set.duration = 350
            TransitionManager.go(mScene2!!, set)
        }
        R.id.select_scene_3 -> {
            // You can also start a transition with a custom TransitionManager.
            mTransitionManagerForScene3!!.transitionTo(mScene3!!)
        }
    }
}
            """.trimIndent())
        }
    }

}
