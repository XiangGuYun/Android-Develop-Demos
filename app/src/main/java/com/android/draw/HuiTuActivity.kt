package com.android.draw

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.R
import com.android.draw.canvas.CanvasActivity
import com.android.draw.canvas.GeometryActivity
import com.android.draw.canvas.NewPathActivity
import com.android.draw.paint.PaintPropertyActivity
import com.android.draw.paint.PaintTextActivity
import com.android.draw.paint.XfermodeActivity
import com.android.draw.xml.ShapeActivity
import com.android.draw.xml.XMLDrawActivity
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
            startActivity(Intent(this, NewPathActivity::class.java))
        }

        btnCanvas.setOnClickListener {
            startActivity(Intent(this, CanvasActivity::class.java))
        }

        btnXMLDraw.setOnClickListener {
            startActivity(Intent(this, XMLDrawActivity::class.java))
        }

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.my_fade_in, R.anim.my_fade_out)
    }

}
