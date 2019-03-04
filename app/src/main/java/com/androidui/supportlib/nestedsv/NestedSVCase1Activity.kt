package com.androidui.supportlib.nestedsv

import android.os.Bundle
import android.widget.ScrollView
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_nested_svcase1.*

@LayoutId(R.layout.activity_nested_svcase1)
class NestedSVCase1Activity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        RVUtils(rvTest)
                .decorate(true)
                .rvAdapter((1..30).toList(),{
            holder, pos ->
            holder.text(R.id.tvCell, "Item${pos+1}")
        },R.layout.item_tv)
    }
}
