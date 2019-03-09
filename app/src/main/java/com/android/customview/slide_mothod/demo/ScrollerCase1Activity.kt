package com.android.customview.slide_mothod.demo

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_scroller_case1.*

@LayoutId(R.layout.activity_scroller_case1)
class ScrollerCase1Activity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        tv1.click {
            scroll1.smoothScrollBy(tv1.width-srnWidth,0, 1000)
        }

        tv2.click {
            scroll2.smoothScrollTo(tv2.width-srnWidth,0,1000)
        }

    }



}
