package com.android.systemui.popupwindow

import android.os.Bundle
import android.view.Gravity
import com.android.R
import com.android.common.dialog.ListBtmDialog
import com.android.systemui.popupwindow.LayoutGravity.*
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.window.PopupUtils
import kotlinx.android.synthetic.main.activity_popup_window.*
import kotlinx.android.synthetic.main.item_tv.view.*
import android.view.WindowManager

@LayoutId(R.layout.activity_popup_window)
class PopupWindowActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        header1.setLeftClick {
            codeDialog.text("""
// 用于PopupWindow的View
val contentView = LayoutInflater.from(this).inflate(R.layout.item_tv, null, false)
// 创建PopupWindow对象，其中：
// 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
// 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
val window = PopupWindow(contentView, 100, 100, true)
// 设置PopupWindow的背景
window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
// 设置PopupWindow是否能响应外部点击事件
window.isOutsideTouchable = true
// 设置PopupWindow是否能响应点击事件
window.isTouchable = true
// 显示PopupWindow，其中：
// 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
window.showAsDropDown(btn1, 0, 0)
// 或者也可以调用此方法显示PopupWindow，其中：
// 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
// 第三和第四个参数分别是PopupWindow相对父View的x、y偏移
// window.showAtLocation(parent, gravity, x, y);
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
btn1.click {
    val pu = PopupUtils(this, R.layout.item_tv, srnWidth to 40.dp2px(), {
        "消失了".toast()
    }, "#ff6666")
    pu.windowView.tvCell.text = "弹窗文字"
    pu.window.showAsDropDown(btn1)
}
            """.trimIndent())
        }

        btn1.click {
            val pu = PopupUtils(this, R.layout.item_tv, srnWidth to 40.dp2px(), {
                "消失了".toast()
            }, "#ff6666")
            pu.windowView.tvCell.text = "弹窗文字"
            pu.window.showAsDropDown(btn1)
        }

        header2.click {
            codeDialog.text("""
btn2.click {
    val pu = PopupUtils(this, R.layout.item_tv, srnWidth to 40.dp2px(), {
        "消失了".toast()
        val attr = window.attributes
        attr.alpha = 1.0f
        window.attributes = attr
    }, "#ff6666")
    pu.windowView.tvCell.text = "弹窗文字"
    pu.window.showAsDropDown(btn1)

    val attr = window.attributes
    attr.alpha = 0.6f
    window.attributes = attr
}
            """.trimIndent())
        }

        btn2.click {
            val pu = PopupUtils(this, R.layout.item_tv, srnWidth to 40.dp2px(), {
                "消失了".toast()
                val attr = window.attributes
                attr.alpha = 1.0f
                window.attributes = attr
            }, "#ff6666")
            pu.windowView.tvCell.text = "弹窗文字"
            pu.window.showAsDropDown(btn1)

            val attr = window.attributes
            attr.alpha = 0.6f
            window.attributes = attr
        }

        val window = CommonPopupWindow(this, R.layout.item_tv, 200.dp2px(), 40.dp2px()).get { it.tvCell.text = "Hello Common" }

        val listDialog = ListBtmDialog(
                this,
                "选择Anchor",
                arrayListOf("ALIGN_LEFT","ALIGN_ABOVE","ALIGN_RIGHT","ALIGN_BOTTOM", "TO_LEFT","TO_ABOVE","TO_RIGHT","TO_BOTTOM","CENTER_HORI","CENTER_VERT"))
        {
            dialog, view, pos ->
            view.click {
                window.showBashOfAnchor(btnCommon, LayoutGravity().setVertGravity(when(pos){
                    0->ALIGN_LEFT
                    1->ALIGN_ABOVE
                    2->ALIGN_RIGHT
                    3->ALIGN_BOTTOM
                    4->TO_LEFT
                    5->TO_ABOVE
                    6->TO_RIGHT
                    7->TO_BOTTOM
                    8->CENTER_HORI
                    else->CENTER_VERT
                }), 0,0)
                dialog.dismiss()
            }
        }

        btnCommon.click {
            listDialog.show()
        }

        header3.setLeftClick {
            webDialog.url("view/popupwindow")
        }

        var xoff = 0
        var yoff = 0
        btn3.click {
            val pu = PopupUtils(this, R.layout.item_tv, 100.dp2px() to 40.dp2px(), {}, "#ff6666")
            pu.windowView.tvCell.text = "弹窗文字"
            pu.window.showAsDropDown(btn3, xoff, yoff)
        }

        sbX.change { seekBar, progress, fromUser ->
            xoff = progress*2
        }

        sbY.change { seekBar, progress, fromUser ->
            yoff = progress*2
        }

        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                if(xoff>0){
                    xoff *= -1
                    yoff *= -1
                }
            }else{
                if(xoff<0){
                    xoff *= -1
                    yoff *= -1
                }
            }
        }

        val listDialog1 = ListBtmDialog(
                this,
                "选择Anchor",
                arrayListOf("LEFT","TOP","RIGHT","BOTTOM"))
        {
            dialog, view, pos ->
            view.click {
                val pu = PopupUtils(this, R.layout.item_tv, 100.dp2px() to 40.dp2px(), {}, "#ff6666")
                pu.windowView.tvCell.text = "弹窗文字"
                pu.window.showAtLocation(btn4, when(pos){
                    0->Gravity.LEFT
                    1->Gravity.TOP
                    2->Gravity.RIGHT
                    else->Gravity.BOTTOM
                }, 0, 0)
                dialog.dismiss()
            }
        }

        btn4.click {
            listDialog1.show()
        }

        header5.setRightClick {
            codeDialog.text("""
val pu = PopupUtils(this, R.layout.item_tv2, srnWidth to 300.dp2px(), {
    val lp = getWindow().attributes
    lp.alpha = 1.0f
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    getWindow().attributes = lp
}, "#ff6666")
pu.windowView.tvCell.text = "弹窗文字"
pu.window.animationStyle = R.style.animTranslate
pu.window.showAtLocation(btn5, Gravity.BOTTOM, 0,0)
val lp = getWindow().attributes
lp.alpha = 0.3f
getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
getWindow().attributes = lp
            """.trimIndent())
        }

        btn5.click {
            val pu = PopupUtils(this, R.layout.item_tv2, srnWidth to 300.dp2px(), {
                val lp = getWindow().attributes
                lp.alpha = 1.0f
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                getWindow().attributes = lp
            }, "#ff6666")
            pu.windowView.tvCell.text = "弹窗文字"
            pu.window.animationStyle = R.style.animTranslate
            pu.window.showAtLocation(btn5, Gravity.BOTTOM, 0,0)
            val lp = getWindow().attributes
            lp.alpha = 0.3f
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            getWindow().attributes = lp
        }

    }

}
