package com.androidui.supportlib.recyclerview

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.androidui.R
import com.androidui.dialog.InputDialog
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_hl.*
import kotlinx.android.synthetic.main.dialog_input.*

@LayoutId(R.layout.activity_hl)
class HLActivity : KotlinActivity() {

    val testData = listOf(R.mipmap.img1, R.mipmap.bg_girl1, R.mipmap.img3, R.mipmap.bg_girl, R.mipmap.header1,
            R.mipmap.header2,R.mipmap.header3,R.mipmap.header5,R.mipmap.header6,R.mipmap.header7)

    override fun init(bundle: Bundle?) {

        val dialog = InputDialog(this)

        val rvUtils = RVUtils(rvHL)
                .managerHorizontal()//横向布局
                .rvAdapterHF(testData, //数据源
                        R.layout.header2, //HeaderView布局
                        {
                            headerHolder ->
                            headerHolder.text(R.id.tvHeader, "Header\n开 始")//处理HeaderView
                        },
                        R.layout.header2,
                        {
                            footerHolder ->
                            footerHolder.text(R.id.tvHeader, "Footer\n结 束")//处理HeaderView
                        },
                        {
                            normalHolder, pos ->
                            normalHolder.setImageResource(R.id.ivCell, testData[pos])//处理普通ItemView
                        },
                        {
                            0 //返回普通ItemView的布局下标，从0开始
                        }, R.layout.tv_item_h)//普通ItemView布

        rvHL.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                tvScrollY.text = "X轴滑动距离：${rvUtils.scollXDistance}"
                tv1.text = "FirstVisible：${(rvHL.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()}"
                tv2.text = "FirstCompletelyVisible：${(rvHL.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()}"
                tv3.text = "LastVisible：${(rvHL.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()}"
                tv4.text = "LastCompletelyVisible：${(rvHL.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()}"
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
                    (rvHL.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(position, 0)
                    dialog.dismiss()
                }
            }
        }
    }

}
