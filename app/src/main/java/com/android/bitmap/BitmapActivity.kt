package com.android.bitmap

import android.Manifest
import android.graphics.BitmapFactory
import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_bitmap2.*
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

@LayoutId(R.layout.activity_bitmap2)
class BitmapActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {

        btn1.click {
            reqPermission(
                    {
                        iv1.setImageBitmap(BitmapFactory.decodeFile("$sdcard_path/headicon.jpg"))
                    },
                    {

                    },
                    Manifest.permission.READ_EXTERNAL_STORAGE
            )

        }

        btn2.click {
            iv2.setImageBitmap(BitmapFactory.decodeResource(resources, R.mipmap.header))
        }

        btn3.click {

        }

        btn4.click {
//            iv4.setImageBitmap(BitmapFactory.decodeByteArray())
        }

    }


}
