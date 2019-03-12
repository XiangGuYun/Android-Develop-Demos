package com.android.customview.event.case2

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_event_case2.*

@LayoutId(R.layout.activity_event_case2)
class EventCase2Activity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        ll.tvParentLog = tvParentLog
    }
}
