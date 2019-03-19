package com.android.systemui

import android.os.Bundle
import com.android.R
import com.android.systemui.edittext.EditTextActivity
import com.android.systemui.imageview.ImageViewActivity
import com.android.systemui.imageview.ImageViewExtendActivity
import com.android.systemui.popupwindow.PopupWindowActivity
import com.android.systemui.scrollview.ScrollViewActivity
import com.android.systemui.status_bar.StatusBarActivity
import com.android.systemui.surfaceview.SurfaceViewActivity
import com.android.systemui.textureview.TextureViewActivity
import com.android.systemui.textview.TextViewActivity
import com.android.systemui.textview.TextViewExtendActivity
import com.android.systemui.webview.WebViewActivity
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_system_ui.*

@LayoutId(R.layout.activity_system_ui)
class SystemUIActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        btnTextView.click {
            go(TextViewActivity::class.java)
        }
        btnTextViewExtend.click {
            go(TextViewExtendActivity::class.java)
        }
        btnEditText.click {
            go(EditTextActivity::class.java)
        }
        btnImageView.click {
            go(ImageViewActivity::class.java)
        }
        btnImageViewExtend.click {
            go(ImageViewExtendActivity::class.java)
        }
        btnScrollView.click {
            go(ScrollViewActivity::class.java)
        }
        btnWebView.click {
            go(WebViewActivity::class.java)
        }
        btnSurfaceView.click {
            go(SurfaceViewActivity::class.java)
        }
        btnTextureView.click {
            go(TextureViewActivity::class.java)
        }
        btnStatusBar.click {
            go(StatusBarActivity::class.java)
        }
        btnPopupWindow.click {
            go(PopupWindowActivity::class.java)
        }
    }
}
