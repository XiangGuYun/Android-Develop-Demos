package com.androidui.supportlib.recyclerview

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import com.androidui.R
import com.androidui.dialog.InputDialog
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_rvtest2.*
import kotlinx.android.synthetic.main.dialog_input.*

@LayoutId(R.layout.activity_rvtest2)
class RVTest2Activity : KotlinActivity() {
    override fun init(bundle: Bundle?) {

        val dialog = InputDialog(this)

        val list = ArrayList<String>()
        for (i in 1..100){
            list.add("ITEM"+(i+1))
        }
        val rvUtils = RVUtils(rv1)
        val heights = ArrayList<Int>()
        for (i in 1..100){
            heights.add((100 + Math.random() * 300).toInt())
        }
        rvUtils.staggerManager(4,true)
                .anim(null)
                .enableDraggableItem(list,true)
                .rvAdapter(list, { holder, pos ->
                    holder.tv(R.id.tv_cell).let {
                        it.layoutParams.height = heights[pos]
                        it.setBackgroundColor("#${getRandColorCode()}".color())
                    }
                    holder.text(R.id.tv_cell, list[pos])
                            .itemClick { rvUtils.doDelete(pos, list.size) }
                            .itemLongClick {
                                if(pos!=0) rvUtils.getmItemTouchHelper().startDrag(holder)
                            }
                },R.layout.stagger_cell)

        var move = 0
        rv1.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                move += dy
                tv0.text = "Y轴移动距离：$move"
            }
        })

        tvScrollTo.click {
            dialog.show()
            dialog.tvYes.click {
                if(dialog.etMain.textString.isNotEmpty()){
                    val position = dialog.etMain.textString.toInt()
                    if(position>list.size-1||position<0){
                        "不能数组越界".toast()
                        return@click
                    }
                    (rv1.layoutManager as StaggeredGridLayoutManager).scrollToPositionWithOffset(position, 0)
                    dialog.dismiss()
                }
            }
        }

    }
}
