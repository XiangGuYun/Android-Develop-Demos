package com.androidui.supportlib.rv

import android.os.Build
import android.os.Bundle
import android.view.animation.*
import com.androidui.R
import com.androidui.dialog.InputDialog
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import jp.wasabeef.recyclerview.animators.*
import kotlinx.android.synthetic.main.activity_rvanim.*
import kotlinx.android.synthetic.main.dialog_input.*
import java.util.*


@LayoutId(R.layout.activity_rvanim)
class RVAnimActivity : KotlinActivity() {

    val testData = arrayListOf(R.mipmap.img1, R.mipmap.header1, R.mipmap.img3)

    val testDataCopy = arrayListOf(R.mipmap.img1, R.mipmap.header1, R.mipmap.img3, R.mipmap.bg_girl, R.mipmap.header1,
            R.mipmap.header2,R.mipmap.header3,R.mipmap.header5,R.mipmap.header6,R.mipmap.header7)


    val options1Items = listOf(
                    "Landing", "ScaleIn", "ScaleInTop", "ScaleInBottom",
                    "ScaleInLeft", "ScaleInRight", "FadeIn", "FadeInDown",
            "FadeInUp", "FadeInLeft", "FadeInRight", "FlipInTopX",
    "FlipInBottomX", "FlipInLeftY", "FlipInRightY", "SlideInLeft",
    "SlideInRight", "OvershootInLeft", "OvershootInRight", "SlideInUp", "SlideInDown")

    val options2Items = listOf(
            "Linear",
            "Accelerate",
            "Decelerate",
            "Overshoot",
            "AccelerateDecelerate",
            "Anticipate",
            "AnticipateOvershoot",
            "Bounce",
            "Cycle"
    )

    val animList = listOf(
            LandingAnimator(), ScaleInAnimator(), ScaleInTopAnimator(), ScaleInBottomAnimator(),
            ScaleInLeftAnimator(), ScaleInRightAnimator(), FadeInAnimator(), FadeInDownAnimator(),
            FadeInUpAnimator(), FadeInLeftAnimator(), FadeInRightAnimator(), FlipInTopXAnimator(),
            FlipInBottomXAnimator(), FlipInLeftYAnimator(), FlipInRightYAnimator(), SlideInLeftAnimator(),
            SlideInRightAnimator(), OvershootInLeftAnimator(), OvershootInRightAnimator(), SlideInUpAnimator(), SlideInDownAnimator())

    private lateinit var dialog: InputDialog

    private var addAnimTime: Long = 1000

    private var deleteAnimTime: Long = 200

    override fun init(bundle: Bundle?) {
        dialog = InputDialog(this)
        rv1.itemAnimator?.apply {
            addDuration = 1000
        }
        val rvUtils = RVUtils(rv1)
                .anim(SlideInLeftAnimator().apply {
                    addDuration = addAnimTime
                    removeDuration = deleteAnimTime
                    moveDuration = 100
                    changeDuration = 0
                    setInterpolator(AccelerateInterpolator())
                })
                .rvAdapter(testData,{
                    holder, pos ->
                    holder.getItemView().tag = pos.toString()
                    holder.ir(R.id.ivCell, testData[pos])
                            .itemClick {
                                testData.removeAt(pos)
                                rv1.adapter?.notifyItemRemoved(pos)
                                //关于删除后位置错乱的问题https://www.cnblogs.com/zhujiabin/p/6737117.html
                                if (pos != testData.size) {
                                    rv1.adapter?.notifyItemRangeChanged(pos, testData.size - pos)
                                }
                            }
                }, R.layout.iv_item)


        btnInsert.click {
            testData.add(testData.size,testDataCopy[Random().nextInt(testDataCopy.size)])
            rv1.adapter?.notifyItemInserted(testData.lastIndex)
            rv1.adapter?.notifyItemRangeChanged(testData.size-1, 1)
        }

        val pvOptions = OptionsPickerBuilder(this, OnOptionsSelectListener {
            options1, options2, options3, v ->
            rvUtils.anim(animList[options1].apply {
                addDuration = addAnimTime
                removeDuration = deleteAnimTime
                moveDuration = 100
                changeDuration = 0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    setInterpolator(when(options2){
                        0->LinearInterpolator()
                        1->AccelerateInterpolator()
                        2->DecelerateInterpolator()
                        3->OvershootInterpolator()
                        4->AccelerateDecelerateInterpolator()
                        5->AnticipateInterpolator()
                        6->AnticipateOvershootInterpolator()
                        7->BounceInterpolator()
                        else->CycleInterpolator(0.5f)
                    })
                }
            })
         }
        ).build<Any>()

        pvOptions.setNPicker(options1Items, options2Items, null)

        btnSelectAnim.click {
            pvOptions.show()
        }

        btnAddTime.click {
            dialog.show()
            dialog.etMain.setText("1000")
            dialog.tvYes.click {
                addAnimTime = dialog.etMain.textString.toLong()
                rv1.itemAnimator?.addDuration = addAnimTime
                dialog.dismiss()
            }
        }

        btnDeleteTime.click {
            dialog.show()
            dialog.etMain.setText("200")
            dialog.tvYes.click {
                deleteAnimTime = dialog.etMain.textString.toLong()
                rv1.itemAnimator?.removeDuration = deleteAnimTime
                dialog.dismiss()
            }
        }

    }



}
