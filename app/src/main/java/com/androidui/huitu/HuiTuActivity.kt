package com.androidui.huitu

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidui.R
import com.androidui.huitu.canvas.CanvasActivity
import com.androidui.huitu.canvas.GeometryActivity
import com.androidui.huitu.canvas.PathActivity
import com.androidui.huitu.paint.PaintPropertyActivity
import com.androidui.huitu.paint.PaintTextActivity
import com.androidui.huitu.paint.XfermodeActivity
import com.kotlinlib.view.recyclerview.RVInterface
import kotlinx.android.synthetic.main.activity_hui_tu.*

class HuiTuActivity : AppCompatActivity(),RVInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hui_tu)

        btnPaintProperty.setOnClickListener {
            startActivity(Intent(this, PaintPropertyActivity::class.java))
        }

        btnPaintText.setOnClickListener {
            startActivity(Intent(this, PaintTextActivity::class.java))
        }

        btnXfermode.setOnClickListener {
            startActivity(Intent(this, XfermodeActivity::class.java))
        }

        btnGeometry.setOnClickListener {
            startActivity(Intent(this, GeometryActivity::class.java))
        }

        btnPath.setOnClickListener {
            startActivity(Intent(this, PathActivity::class.java))
        }

        btnCanvas.setOnClickListener {
            startActivity(Intent(this, CanvasActivity::class.java))
        }

    }
}
