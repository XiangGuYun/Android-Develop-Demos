package com.android.draw.xml

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_xmldraw.*

@LayoutId(R.layout.activity_xmldraw)
class XMLDrawActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        btnShape.click {
            go(ShapeActivity::class.java)
        }
        btnLayer.click {
            go(LayerActivity::class.java)
        }
        btnBitmap.click {
            go(BitmapActivity::class.java)
        }
    }
}
