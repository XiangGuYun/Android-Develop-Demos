package com.androidui.supportlib.rv

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.helper.ItemTouchHelper
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_rvtest4.*

@LayoutId(R.layout.activity_rvtest4)
class RVTest4Activity : KotlinActivity() {

    val testData = arrayListOf(R.mipmap.img1, R.mipmap.bg_girl1, R.mipmap.img3, R.mipmap.bg_girl, R.mipmap.header1,
            R.mipmap.header2,R.mipmap.header3,R.mipmap.header5,R.mipmap.header6,R.mipmap.header7)

    override fun init(bundle: Bundle?) {
        RVUtils(rvDragDelete)
                .decorate(true)
                .enableDragDeleteItem(ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,0,0)
                .rvAdapter(testData,{
                    holder, pos ->
                    holder.ir(R.id.ivItem, testData[pos])
                },R.layout.rv_item1)
    }

}
