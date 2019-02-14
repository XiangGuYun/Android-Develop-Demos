package com.androidui.supportlib

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import com.androidui.R
import com.androidui.supportlib.adapter.Vp1Adapter
import com.androidui.supportlib.adapter.Vp2Adapter
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_view_pager.*
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import java.nio.file.Files.size




@LayoutId(R.layout.activity_view_pager)
class ViewPagerActivity : KotlinActivity() {

    companion object {
        var MIN_SCALE = 0.45f//数值越小，切换过去的图变化越明显
        val MAX_ROTATION = 20.0f
        val MAX_TRANSLATE = 40.0f//数值越大越容易滑动
    }

    override fun init(bundle: Bundle?) {

        initMagicIndidator()

        header1.setLeftClick {
            codeDialog.text("""
                PageAdapter 必须重写的四个函数：

                //一般返回arg0==arg1即可
                boolean isViewFromObject(View arg0, Object arg1)

                //返回要滑动的View的个数
                int getCount() boolean

                //从当前container中删除指定位置（position）的View
                void destroyItem(ViewGroup container, int position,Object object)

                //第一：将当前视图添加到container中，第二：返回当前View
                Object instantiateItem(ViewGroup container, int position)
            """.trimIndent())
        }

        header1.subTitle.click {
            codeDialog.text("""
                //数据源
                val list = listOf(ImageView(this),
                ImageView(this),
                ImageView(this))
                .apply {
                    forEach {
                        it.scaleType = ImageView.ScaleType.CENTER_CROP
                    }
                }

                //设置适配器
                vp1.adapter = Vp1Adapter(list)

                //定义适配器
                class Vp1Adapter(var datas: List<ImageView>) : PagerAdapter() {

                    override fun isViewFromObject(p0: View, p1: Any): Boolean {
                        return p0 == p1
                    }

                    override fun getCount(): Int {
                        return datas.size
                    }

                    //初始化Item
                    override fun instantiateItem(container: ViewGroup, position: Int): Any {
                        //获取到对应位置的视图
                        val view = datas[position]

                        //对视图进行处理
                        view.setImageResource(when(position){
                            0->R.mipmap.header1
                            1->R.mipmap.header2
                            else->R.mipmap.header3
                        })

                        //添加到容器中
                        container.addView(view)

                        //将视图返回
                        return view
                    }

                    //移除Item
                    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                        container.removeView(datas[position])
                    }

                }
            """.trimIndent())
        }

        val list = listOf(ImageView(this),ImageView(this),ImageView(this)).apply {
            forEach {
                it.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }

        val list1 = listOf(ImageView(this),ImageView(this),ImageView(this)).apply {
            forEach {
                it.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }

        val list2 = listOf(ImageView(this),ImageView(this),ImageView(this)).apply {
            forEach {
                it.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }

        val list4 = listOf(ImageView(this),ImageView(this),ImageView(this),ImageView(this),ImageView(this),ImageView(this),ImageView(this)).apply {
            forEach {
                it.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }

        vp1.adapter = Vp1Adapter(list)
        vp2.adapter = Vp1Adapter(list1)
        vp3.adapter = Vp1Adapter(list2)
        vp4.adapter = Vp2Adapter(this, list4)
        vp4.pageMargin = 3

        vp2.setPageTransformer(true, ScalePageTransformer())
        vp3.setPageTransformer(true, RotatePageTransformer())
        vp4.setPageTransformer(true, GalleryPageTransformer())
    }

    private fun initMagicIndidator() {
//        val magicIndicator = findViewById<View>(R.id.magic_indicator) as MagicIndicator
//        val commonNavigator = CommonNavigator(this)
//        commonNavigator.setAdapter(object : CommonNavigatorAdapter() {
//
//            val count: Int
//                get() = if (mTitleDataList == null) 0 else mTitleDataList.size()
//
//            fun getTitleView(context: Context, index: Int): IPagerTitleView {
//                val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
//                colorTransitionPagerTitleView.setNormalColor(Color.GRAY)
//                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK)
//                colorTransitionPagerTitleView.setText(mTitleDataList.get(index))
//                colorTransitionPagerTitleView.setOnClickListener(View.OnClickListener { mViewPager.setCurrentItem(index) })
//                return colorTransitionPagerTitleView
//            }
//
//            fun getIndicator(context: Context): IPagerIndicator {
//                val indicator = LinePagerIndicator(context)
//                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT)
//                return indicator
//            }
//        })
//        magicIndicator.setNavigator(commonNavigator)
    }

    inner class GalleryPageTransformer : ViewPager.PageTransformer {

        override fun transformPage(page: View, position: Float) {
            if (position < -1) {
                page.translationX = MAX_TRANSLATE
                page.scaleX = MIN_SCALE
                page.scaleY = MIN_SCALE
                page.rotationY = -MAX_ROTATION
            } else if (position <= 0) {
                page.translationX = -MAX_TRANSLATE * position
                val scale = MIN_SCALE + (1 - MIN_SCALE) * (1.0f + position)
                page.scaleX = scale
                page.scaleY = scale
                page.rotationY = MAX_ROTATION * position
            } else if (position <= 1) {
                page.translationX = -MAX_TRANSLATE * position
                val scale = MIN_SCALE + (1 - MIN_SCALE) * (1.0f - position)
                page.scaleX = scale
                page.scaleY = scale
                page.rotationY = MAX_ROTATION * position
            } else {
                page.translationX = -MAX_TRANSLATE
                page.scaleX = MIN_SCALE
                page.scaleY = MIN_SCALE
                page.rotationY = MAX_ROTATION
            }
        }

    }

    inner class RotatePageTransformer : ViewPager.PageTransformer {

        override fun transformPage(page: View, position: Float) {
            if (position < -1)
                rotate(page, -MAX_ROTATION)
            else if (position <= 1)
                rotate(page, MAX_ROTATION * position)
            else
                rotate(page, MAX_ROTATION)
        }

        private fun rotate(view: View, rotation: Float) {
            view.pivotX = view.width * 0.5f
            view.pivotY = view.height.toFloat()
            view.rotation = rotation
        }

    }

    class ScalePageTransformer : ViewPager.PageTransformer {

        override fun transformPage(page: View, position: Float) {
            //Log.d("TAG", "<"+page.hashCode()+", "+position+">");
            // out of left screen
            if (position < -1.0f) {
                page.scaleX = MIN_SCALE
                page.scaleY = MIN_SCALE
            } else if (position <= 0.0f) {
                page.alpha = 1.0f
                page.translationX = 0.0f
                page.scaleX = 1.0f
                page.scaleY = 1.0f
            } else if (position <= 1.0f) {
                page.alpha = 1.0f - position
                page.translationX = -page.width * position
                val scale = MIN_SCALE + (1.0f - MIN_SCALE) * (1.0f - position)
                page.scaleX = scale
                page.scaleY = scale
            } else {
                page.scaleX = MIN_SCALE
                page.scaleY = MIN_SCALE
            }// out of right screen
            // slide right
            // slide left
        }

    }

}
