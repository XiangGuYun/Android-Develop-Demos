package com.androidui.fragment.case2

import com.androidui.R
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.KotlinFragment
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.fragment_case2_list.*

@LayoutId(R.layout.fragment_case2_list)
class Case2ListFragment:KotlinFragment(){
    override fun init() {
        val list = (1..10).toList().map {
            "item$it"
        }
        RVUtils(rv).rvAdapter(list,{
            holder, pos ->
            holder.text(R.id.tvCell, list[pos]).itemClick {
                bus.post(msg.setWhat(0x111).setObj("Book${pos+1}"))
            }
        }, R.layout.item_tv1)
    }

}
