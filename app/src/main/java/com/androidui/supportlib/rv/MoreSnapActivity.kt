package com.androidui.supportlib.rv

import android.os.Bundle
import android.view.Gravity
import com.androidui.R
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_more_snap.*

@LayoutId(R.layout.activity_more_snap)
class MoreSnapActivity : KotlinActivity() {

    val testData = listOf(R.mipmap.img1, R.mipmap.header1, R.mipmap.img3, R.mipmap.bg_girl, R.mipmap.header1,
            R.mipmap.header2,R.mipmap.header3,R.mipmap.header5,R.mipmap.header6,R.mipmap.header7)

    override fun init(bundle: Bundle?) {
        RVUtils(rv1)
                .managerHorizontal()
                .customSnap(GravitySnapHelper(Gravity.START))
                .rvAdapter(testData,{
                    holder, pos ->
                    holder.setImageResource(R.id.iv, testData[pos])
                },R.layout.snaphelper_recycle_item)

        RVUtils(rv2)
                .managerHorizontal()
                .customSnap(GravitySnapHelper(Gravity.END))
                .rvAdapter(testData,{
                    holder, pos ->
                    holder.setImageResource(R.id.iv, testData[pos])
                },R.layout.snaphelper_recycle_item)

        RVUtils(rv3)
                .customSnap(GravitySnapHelper(Gravity.TOP))
                .rvAdapter(testData,{
                    holder, pos ->
                    holder.setImageResource(R.id.iv, testData[pos])
                },R.layout.snaphelper_recycle_item1)
    }

}
