package com.android.anim

import android.graphics.Point
import android.os.Bundle
import android.view.animation.LinearInterpolator
import com.android.R
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.easyandroidanimations.library.*
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_easy_android_animations.*

@LayoutId(R.layout.activity_easy_android_animations)
class EasyAndroidAnimations : KotlinActivity() {

    var listAnimName = listOf(
            "Blind","Blink","Bounce","Explode","Fade In",
            "Fade Out","Flip Horizontal", "Flip Horizontal To","Flip Vertical","Flip Vertical To",
            "Fold In(暂未实现)", "Fold Out(暂未实现)",
            "Highlight","Path","Puff In","Puff Out","Rotate","Scale In","Scale Out",
            "Shake","Slide In","Slide In Underneath",
            "Slide Out","Slide Out Underneath","Transfer")

    override fun init(bundle: Bundle?) {

        val pvOptions = OptionsPickerBuilder(this, OnOptionsSelectListener {
            options1, options2, options3, v ->
            when(options1){
                0-> BlindAnimation(ivTest).animate()
                1-> BlinkAnimation(ivTest).animate()
                2-> BounceAnimation(ivTest).setNumOfBounces(3).setDuration(1000).animate()
                3-> ExplodeAnimation(ivTest).animate()
                4-> FadeInAnimation(ivTest).animate()
                5-> FadeOutAnimation(ivTest).animate()
                6-> FlipHorizontalAnimation(ivTest).animate()
                7-> FlipHorizontalToAnimation(ivTest).setFlipToView(ivTest).setInterpolator(LinearInterpolator()).animate()
                8-> FlipVerticalAnimation(ivTest).animate()
                9-> FlipVerticalToAnimation(ivTest).setFlipToView(ivTest).setInterpolator(LinearInterpolator()).animate()
//                10->FoldInAnimation(ivTest).setFolds(10).setOrientation(FoldLayout.Orientation.HORIZONTAL).animate()
//                11->FoldOutAnimation(ivTest).setFolds(10).setOrientation(FoldLayout.Orientation.HORIZONTAL).animate()
                12->HighlightAnimation(ivTest).animate()
                13->{
                    val points = ArrayList<Point>()
                    PathAnimation(ivTest).setPoints(points).setDuration(2000).animate()
                }
                14->PuffInAnimation(ivTest).animate()
                15->PuffOutAnimation(ivTest).animate()
                16->RotationAnimation(ivTest).setPivot(RotationAnimation.PIVOT_TOP_LEFT).animate()
                17->ScaleInAnimation(ivTest).animate()
                18->ScaleOutAnimation(ivTest).animate()
                19->ShakeAnimation(ivTest).setNumOfShakes(3).setDuration(1000).animate()
                20->SlideInAnimation(ivTest).setDirection(Animation.DIRECTION_UP).animate()
                21->SlideInUnderneathAnimation(ivTest).setDirection(Animation.DIRECTION_DOWN).animate()
                22->SlideOutAnimation(ivTest).setDirection(Animation.DIRECTION_LEFT).animate()
                23->SlideOutUnderneathAnimation(ivTest).setDirection(Animation.DIRECTION_RIGHT).animate()
                24->TransferAnimation(ivTest).setDestinationView(ivTest1).animate()
            }
        }
        ).build<Any>()

        pvOptions.setNPicker(listAnimName, null, null)

        btnSelect.click {
            pvOptions.show()
        }

        btnReset.click {
            go(EasyAndroidAnimations::class.java){
                android.R.anim.fade_in to android.R.anim.fade_out
            }
            finish()
        }

    }

}
