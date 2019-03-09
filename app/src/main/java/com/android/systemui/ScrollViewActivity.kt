package com.android.systemui

import android.os.Bundle
import com.android.R
import com.android.systemui.test.SV1Activity
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_scroll_view.*

@LayoutId(R.layout.activity_scroll_view)
class ScrollViewActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        btnTest1.click {
            go(SV1Activity::class.java)
        }
        header1.setRightClick {
          codeDialog.text("""
            btnTest.setOnClickListener {
                sv.fullScroll(ScrollView.FOCUS_DOWN)
            }
            btnTest1.setOnClickListener {
                sv.fullScroll(ScrollView.FOCUS_UP)
            }
          """.trimIndent())
        }
    }
}
