package com.android.systemui.scrollview

import android.os.Bundle
import com.android.R
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

        btnTest2.click {
            go(SV2Activity::class.java)
        }

        header3.setLeftClick {
            codeDialog.text("""
作用：给RecyclerView, ListView, GridView, ScrollView等具有over-scroll-over属性的View提供滑动到顶边界时弹性效果。

项目地址：https://github.com/EverythingMe/overscroll-decor。

ScrollView中使用
OverScrollDecoratorHelper.setUpOverScroll(scrollView)
            """.trimIndent())
        }

        btnTest3.click {
            go(SV3Activity::class.java)
        }

    }
}
