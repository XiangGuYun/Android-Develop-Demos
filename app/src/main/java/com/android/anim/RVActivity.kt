package com.android.anim

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_rv.*

@LayoutId(R.layout.activity_rv)
class RVActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        val listTest = (1..10).toMutableList()
        RVUtils(rvTest).rvAdapter(listTest,{
            holder, pos ->
            holder.text(R.id.tvCell, "I'm item${pos+1}")
            holder.itemClick {
                listTest.removeAt(pos)
                rvTest.update()
            }
        },R.layout.item_anim_rv)
    }
}
