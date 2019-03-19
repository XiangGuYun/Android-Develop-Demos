package com.android.systemui.imageview

import android.os.Bundle
import com.android.R
import com.android.systemui.imageview.cicleimageview.CircleImageViewCase1Activity
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_image_view_extend.*

@LayoutId(R.layout.activity_image_view_extend)
class ImageViewExtendActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        header1.setRightClick {
            codeDialog.text("""
<de.hdodenhof.circleimageview.CircleImageView
        android:id="@+id/profile_image"
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:src="@mipmap/love"
        app:civ_border_width="5dp"
        android:layout_gravity="center"
        android:layout_marginTop="10dp"
        app:civ_border_color="#ff6666"/>

<de.hdodenhof.circleimageview.CircleImageView
    android:id="@+id/profile_image1"
    android:layout_width="200dp"
    android:layout_height="200dp"
    android:src="@mipmap/img1"
    app:civ_border_width="15dp"
    android:layout_gravity="center"
    android:layout_marginTop="10dp"
    app:civ_border_color="#66ff66"/>

<de.hdodenhof.circleimageview.CircleImageView
    android:id="@+id/profile_image3"
    android:layout_width="200dp"
    android:layout_height="200dp"
    android:src="@mipmap/header5"
    app:civ_border_width="1dp"
    android:layout_gravity="center"
    android:layout_marginTop="10dp"
    app:civ_border_color="#6666ff"/>
            """.trimIndent())
        }

        btn1.setOnClickListener {
            go(CircleImageViewCase1Activity::class.java)
        }

    }

}
