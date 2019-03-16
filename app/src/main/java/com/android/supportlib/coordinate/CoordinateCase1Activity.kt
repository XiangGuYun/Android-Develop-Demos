package com.android.supportlib.coordinate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_coordinate_case1.*

@LayoutId(R.layout.activity_coordinate_case1)
class CoordinateCase1Activity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        val data = (1..100).toList().map {
            "我是第${it}个item"
        }
        RVUtils(rv).decorate(true).
                rvAdapter(data,
                {
                    holder, pos ->
                    holder.text(R.id.tvCell, data[pos])
                }, R.layout.item_tv)

    }

}
