package com.androidui.supportlib.rv

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_hl.*

@LayoutId(R.layout.activity_hl)
class HLActivity : KotlinActivity() {

    val testData = listOf(R.mipmap.img1, R.mipmap.bg_girl1, R.mipmap.img3, R.mipmap.bg_girl, R.mipmap.header1,
            R.mipmap.header2,R.mipmap.header3,R.mipmap.header5,R.mipmap.header6,R.mipmap.header7)

    override fun init(bundle: Bundle?) {
        RVUtils(rvHL)
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
    }

}
