package com.androidui.supportlib.rv

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_rvtest3.*

@LayoutId(R.layout.activity_rvtest3)
class RVTest3Activity : KotlinActivity() {

    val testData = listOf(R.mipmap.img1, R.mipmap.bg_girl1, R.mipmap.img3, R.mipmap.bg_girl, R.mipmap.header1,
            R.mipmap.header2,R.mipmap.header3,R.mipmap.header5,R.mipmap.header6,R.mipmap.header7)

    override fun init(bundle: Bundle?) {
        RVUtils(rv3)//传入RecyclerView对象
                .decorate(R.drawable.rect_line, true)
                .rvAdapter(testData, //数据源
                        {
                            normalHolder, pos ->
                            normalHolder.ir(R.id.ivItem, testData[pos])//处理普通ItemView
                        }, R.layout.rv_item1)//普通ItemView布局
//        ItemTouchHelper(object :ItemTouchHelper.Callback(){
//            override fun getMovementFlags(p0: RecyclerView, p1: RecyclerView.ViewHolder): Int {
//                val dragFlags = ItemTouchHelper.LEFT
//                val swipeFlags = 0
//                return makeMovementFlags(dragFlags, swipeFlags)
//            }
//
//            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
//                return true
//            }
//
//            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
//            }
//        }).attachToRecyclerView(rv3)
    }
}
