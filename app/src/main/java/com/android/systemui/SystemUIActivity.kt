package com.android.systemui

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_system_ui.*

@LayoutId(R.layout.activity_system_ui)
class SystemUIActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        btnTextView.click {
            go(TextViewActivity::class.java)
        }
        btnEditText.click {
            go(EditTextActivity::class.java)
        }
        btnImageView.click {
            go(ImageViewActivity::class.java)
        }
        btnScrollView.click {
            go(ScrollViewActivity::class.java)
        }
        btnWebView.click {
            go(WebViewActivity::class.java)
        }
    }
}
