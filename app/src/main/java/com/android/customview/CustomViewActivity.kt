package com.android.customview

import android.os.Bundle
import com.android.R
import com.android.customview.case_scrollerlayout.ScrollerLayoutActivity
import com.android.customview.view_study.ViewActivity
import com.android.customview.drag.ViewDragHelperActivity
import com.android.customview.event.EventActivity
import com.android.customview.measure_layout.MeasureLayoutActivity
import com.android.customview.slide_mothod.ViewSlideMethodActivity
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_custom_view.*

/**
 * 自定义View
 */
@LayoutId(R.layout.activity_custom_view)
class CustomViewActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        btnView.click {
            go(ViewActivity::class.java)
        }

        btnViewSlideMethod.click {
            go((ViewSlideMethodActivity::class.java))
        }

        btnViewDragHelper.click {
            go(ViewDragHelperActivity::class.java)
        }

        btnViewMeasureLayout.click {
            go(MeasureLayoutActivity::class.java)
        }

        btnViewEvent.click {
            go(EventActivity::class.java)
        }

        btnScrollerLayout.click {
            go(ScrollerLayoutActivity::class.java)
        }

    }

}
