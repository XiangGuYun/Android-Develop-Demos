package com.android.supportlib.adapter

import android.content.Context
import android.graphics.*
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.android.R

class Vp2Adapter(var ctx:Context, var datas: List<ImageView>) : PagerAdapter() {


    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return datas.size
    }

    override fun getPageWidth(position: Int): Float {
        return 0.8f
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = datas[position]
//        val lp = FrameLayout.LayoutParams(
//                150,150)
//        lp.gravity = Gravity.CENTER
//        view.layoutParams = lp
        view.setImageBitmap(getReverseBitmapById(ctx, when(position){
            0-> R.mipmap.header1
            1-> R.mipmap.header2
            2-> R.mipmap.header3
            3-> R.mipmap.headr4
            4-> R.mipmap.header5
            5-> R.mipmap.header6
            6-> R.mipmap.header7
            else-> R.mipmap.header3
        }, 0.5f))
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(datas[position])
    }

    /**
     *
     * @param context Context
     * @param resId Int
     * @param percent Float 阴影占原图的比例
     * @return Bitmap
     */
    fun getReverseBitmapById(context: Context, resId: Int, percent: Float): Bitmap {
        // get the source bitmap
        val srcBitmap = BitmapFactory.decodeResource(context.resources, resId)
        // get the tow third segment of the reverse bitmap
        val matrix = Matrix()
        matrix.setScale(1f, -1f)
        val rvsBitmap = Bitmap.createBitmap(srcBitmap, 0, (srcBitmap.height * (1 - percent)).toInt(),
                srcBitmap.width, (srcBitmap.height * percent).toInt(), matrix, false)
        // combine the source bitmap and the reverse bitmap
        val comBitmap = Bitmap.createBitmap(srcBitmap.width,
                srcBitmap.height + rvsBitmap.height + 20, srcBitmap.config)
        val gCanvas = Canvas(comBitmap)
        gCanvas.drawBitmap(srcBitmap, 0f, 0f, null)
        gCanvas.drawBitmap(rvsBitmap, 0f, srcBitmap.height + 20f, null)
        val paint = Paint()
        val shader = LinearGradient(0f, srcBitmap.height + 20f, 0f, comBitmap.height.toFloat(),
                Color.BLACK, Color.TRANSPARENT, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        gCanvas.drawRect(0f, srcBitmap.height + 20f, srcBitmap.width.toFloat(), comBitmap.height.toFloat(), paint)
        return comBitmap
    }

}