package com.android.supportlib.recyclerview

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.android.R
import com.android.common.dialog.InputDialog
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_rvtest0.*
import kotlinx.android.synthetic.main.dialog_input.*

@LayoutId(R.layout.activity_rvtest0)
class RVTest0Activity : KotlinActivity() {

    val testData = listOf(R.mipmap.img1, R.mipmap.bg_girl1, R.mipmap.img3, R.mipmap.bg_girl, R.mipmap.header1,
            R.mipmap.header2,R.mipmap.header3,R.mipmap.header5,R.mipmap.header6,R.mipmap.header7,
            R.mipmap.img1, R.mipmap.bg_girl1, R.mipmap.img3, R.mipmap.bg_girl, R.mipmap.header1,
            R.mipmap.header2,R.mipmap.header3,R.mipmap.header5,R.mipmap.header6,R.mipmap.header7)

    override fun init(bundle: Bundle?) {

        val dialog = InputDialog(this)





        val rvUtils = RVUtils(rv1)//传入RecyclerView对象
                .rvAdapterHF(testData, //数据源
                        R.layout.header1, //HeaderView布局
                        {
                            headerHolder ->
                            val text = headerHolder::setText
                            text(R.id.tvHeader, "Header")//处理HeaderView
                            val itemView = headerHolder::itemView
                            itemView.get().click {  }
                        },
                        R.layout.header1,
                        {
                            footerHolder ->
                            footerHolder.text(R.id.tvHeader, "Footer")//处理HeaderView
                        },
                        {
                            normalHolder, pos ->
                            normalHolder.setImageResource(R.id.ivCell, testData[pos])//处理普通ItemView
                        },
                        {
                            0 //返回普通ItemView的布局下标，从0开始
                        }, R.layout.iv_item)//普通ItemView布局

        rv1.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                tvScrollY.text = "Y轴滑动距离：${rvUtils.scollYDistance}"
                tv1.text = "FirstVisible：${(rv1.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()}"
                tv2.text = "FirstCompletelyVisible：${(rv1.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()}"
                tv3.text = "LastVisible：${(rv1.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()}"
                tv4.text = "LastCompletelyVisible：${(rv1.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()}"
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
                    (rv1.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(position, 0)
                    dialog.dismiss()
                }
            }
        }

    }

}
