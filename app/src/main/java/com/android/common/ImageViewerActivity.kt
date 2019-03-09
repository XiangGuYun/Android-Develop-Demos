package com.android.common

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.R
import kotlinx.android.synthetic.main.activity_image_viewer.*

class ImageViewerActivity : AppCompatActivity() {

    companion object {
        const val IMG_ID = "img_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)
        iv.setImageResource(intent.getIntExtra(IMG_ID, R.mipmap.ic_launcher))
    }
}
