package com.android.supportlib.recyclerview

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.android.R
import com.android.common.dialog.InputDialog
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_rvtest1.*
import kotlinx.android.synthetic.main.dialog_input.*

@LayoutId(R.layout.activity_rvtest1)
class RVTest1Activity : KotlinActivity() {

    val testData = listOf(R.mipmap.img1,R.mipmap.img1, R.mipmap.bg_girl1, R.mipmap.img3, R.mipmap.bg_girl, R.mipmap.header1,
            R.mipmap.header2,R.mipmap.header3,R.mipmap.header5,R.mipmap.header6,R.mipmap.header7,R.mipmap.img1)

    override fun init(bundle: Bundle?) {

        val dialog = InputDialog(this)

        val rvUtils = RVUtils(rv1)//传入RecyclerView对象
                .gridManager(2)//设置2格纵向网格布局
                .rvAdapterHF(testData, //数据源
                        R.layout.header1, //HeaderView布局
                        {
                            headerHolder ->
                            headerHolder.text(R.id.tvHeader, "Header")//处理HeaderView
                        },
                        R.layout.header1,
                        {
                            footerHolder ->
                            footerHolder.text(R.id.tvHeader, "Footer")//处理HeaderView
                        },
                        {
                            normalHolder, pos ->
                            normalHolder.ir(R.id.ivCell, testData[pos])//处理普通ItemView
                        },
                        {
                            0 //返回普通ItemView的布局下标，从0开始
                        }, R.layout.iv_grid_item)//普通ItemView布局

        var move = 0
        rv1.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                move += dy
                tv0.text = "Y轴移动距离：${move}"
                tv1.text = "FirstVisible：${(rv1.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()}"
                tv2.text = "FirstCompletelyVisible：${(rv1.layoutManager as GridLayoutManager).findFirstCompletelyVisibleItemPosition()}"
                tv3.text = "LastVisible：${(rv1.layoutManager as GridLayoutManager).findLastVisibleItemPosition()}"
                tv4.text = "LastCompletelyVisible：${(rv1.layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()}"
            }
        })

        tvScrollTo.click {
            dialog.show()
            dialog.tvYes.click {
                if(dialog.etMain.textString.isNotEmpty()){
                    val position = dialog.etMain.textString.toInt()
                    if(position>testData.size-1||position<0){
                        "不能数组越界".toast()
                        return@click
                    }
                    //rv1.scrollTo(position, testData)
                    (rv1.layoutManager as GridLayoutManager).scrollToPositionWithOffset(position, 0)
                    dialog.dismiss()
                }
            }
        }
    }
}
