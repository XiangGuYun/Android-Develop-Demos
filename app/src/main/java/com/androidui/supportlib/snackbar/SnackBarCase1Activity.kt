package com.androidui.supportlib.snackbar

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_snack_bar_case1.*
import kotlinx.android.synthetic.main.dialog_input_text.*

@LayoutId(R.layout.activity_snack_bar_case1)
class SnackBarCase1Activity : KotlinActivity() {

    var length_state = 0
    var info = "默认信息"

    private var color: Int = Color.RED

    override fun init(bundle: Bundle?) {

        rgLength.check {
            when(it){
                R.id.rbLength1->{
                    length_state = 0
                }
                R.id.rbLength2->{
                    length_state = 1
                }
                R.id.rbLength3->{
                    length_state = 2
                }
            }
        }

        btnSetInfo.click {
            textDialog.show()
            textDialog.tvYes.click {
                info = textDialog.etMain.textString
                textDialog.dismiss()
            }
        }

        btnActionColor.click {
            colorPicker("设置颜色"){
                color = it
            }
        }

        btnShow.click {
            Snackbar.make(fl, info, when(length_state){
                0->Snackbar.LENGTH_LONG
                1->Snackbar.LENGTH_SHORT
                else->Snackbar.LENGTH_INDEFINITE
            })
                    .setAction("close"){
                        "取消了".toast()
                    }
                    .setActionTextColor(color)
                    .show()
        }
    }
}
