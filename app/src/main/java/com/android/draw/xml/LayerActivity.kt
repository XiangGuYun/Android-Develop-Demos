package com.android.draw.xml

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_layer.*

@LayoutId(R.layout.activity_layer)
class LayerActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        header1.setRightClick {
            codeDialog.text("""
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android" >

    <item >
        <shape android:shape="rectangle" >
            <solid android:color="#0000ff"/>
        </shape>
    </item>

    <item android:bottom="25dp" android:top="25dp" android:left="25dp" android:right="25dp">
        <shape android:shape="rectangle" >
            <solid android:color="#00ff00" />
        </shape>
    </item>

    <item android:bottom="50dp" android:top="50dp" android:left="50dp" android:right="50dp">
        <shape android:shape="rectangle" >
            <solid android:color="#ff0000" />
        </shape>
    </item>
</layer-list>
            """.trimIndent())
        }
    }
}
