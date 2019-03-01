package com.androidui.supportlib.rv

import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_rvtest2.*

@LayoutId(R.layout.activity_rvtest2)
class RVTest2Activity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
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
                .animator(null)
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
    }
}
