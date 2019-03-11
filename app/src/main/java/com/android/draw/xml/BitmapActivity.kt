package com.android.draw.xml

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_bitmap.*

@LayoutId(R.layout.activity_bitmap)
class BitmapActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        header1.setLeftClick {
            webDialog.url("bmp_xml", 140)
        }.setRightClick {
            codeDialog.text("""
<?xml version="1.0" encoding="utf-8"?>
<bitmap xmlns:android="http://schemas.android.com/apk/res/android"
    android:alpha="0.5"
    android:antialias="true"
    android:dither="true"
    android:filter="true"
    android:gravity="clip_horizontal"
    android:src="@mipmap/love"
    >

</bitmap>

<ImageView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:src="@drawable/bitmap"/>

iv.setImageDrawable(resources.getDrawable(R.drawable.bitmap))
            """.trimIndent())
        }

        iv.setImageDrawable(resources.getDrawable(R.drawable.bitmap))


    }

}
