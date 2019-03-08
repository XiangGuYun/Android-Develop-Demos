package com.androidui.customview

import android.os.Bundle
import com.androidui.R
import com.androidui.customview.attr_read.AttrReadActivity
import com.androidui.customview.drag.ViewDragHelperActivity
import com.androidui.customview.event.EventActivity
import com.androidui.customview.function_read.FunctionReadActivity
import com.androidui.customview.measure_layout.MeasureLayoutActivity
import com.androidui.customview.slide_mothod.ViewSlideMethodActivity
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_custom_view.*

/**
 * 自定义View
 */
@LayoutId(R.layout.activity_custom_view)
class CustomViewActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        btnViewAttrRead.click {
            go(AttrReadActivity::class.java)
        }

        btnViewFunctionRead.click {
            go(FunctionReadActivity::class.java)
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

    }

}
