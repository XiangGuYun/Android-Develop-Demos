package com.androidui.supportlib.viewpager

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.androidui.R
import com.androidui.supportlib.adapter.Vp1Adapter
import com.androidui.supportlib.adapter.Vp2Adapter
import com.kotlinlib.IV
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlinx.android.synthetic.main.header_view1.view.*


@LayoutId(R.layout.activity_view_pager)
class ViewPagerActivity : KotlinActivity() {

    companion object {
        var MIN_SCALE = 0.45f//数值越小，切换过去的图变化越明显
        val MAX_ROTATION = 20.0f
        val MAX_TRANSLATE = 40.0f//数值越大越容易滑动
    }

    override fun init(bundle: Bundle?) {

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

        header2.setLeftClick {
            codeDialog.text("""
                知识点1：设置页面转换效果
                setPageTransformer(boolean reverseDrawingOrder, @Nullable ViewPager.PageTransformer transformer)
                参数reverseDrawingOrder：反转绘制顺序，一般设置为true，如果设置为false，那么当两个页面在转换中出现重叠时，上层页面会透明化。
                参数transformer：是接口PageTransformer的对象，需要实现transformPage方法。

                知识点2：transformPage(page: View, position: Float)



            """.trimIndent())
        }

        header2.tvSubTitle.click {
            codeDialog.text("""

                vp2.setPageTransformer(true, ScalePageTransformer())

                class ScalePageTransformer : ViewPager.PageTransformer {

                    override fun transformPage(page: View, position: Float) {
                        when {
                            position < -1.0f -> {
                                page.scaleX = MIN_SCALE
                                page.scaleY = MIN_SCALE
                            }
                            position <= 0.0f -> {
                                page.alpha = 1.0f
                                page.translationX = 0.0f
                                page.scaleX = 1.0f
                                page.scaleY = 1.0f
                            }
                            position <= 1.0f -> {
                                page.alpha = 1.0f - position
                                page.translationX = -page.width * position
                                val scale = MIN_SCALE + (1.0f - MIN_SCALE) * (1.0f - position)
                                page.scaleX = scale
                                page.scaleY = scale
                            }
                            else -> {
                                page.scaleX = MIN_SCALE
                                page.scaleY = MIN_SCALE
                            }
                        }
                    }

                }
            """.trimIndent())
        }

        val list = listOf(
                inf(R.layout.snaphelper_recycle_item_alpha).apply {findViewById<IV>(R.id.iv).setImageResource(R.mipmap.header1)  },
                inf(R.layout.snaphelper_recycle_item_alpha).apply { findViewById<IV>(R.id.iv).setImageResource(R.mipmap.header2) },
                inf(R.layout.snaphelper_recycle_item_alpha).apply { findViewById<IV>(R.id.iv).setImageResource(R.mipmap.header3) }
        )

        val list1 = listOf(
                inf(R.layout.snaphelper_recycle_item_alpha).apply {findViewById<IV>(R.id.iv).setImageResource(R.mipmap.header1)  },
                inf(R.layout.snaphelper_recycle_item_alpha).apply { findViewById<IV>(R.id.iv).setImageResource(R.mipmap.header2) },
                inf(R.layout.snaphelper_recycle_item_alpha).apply { findViewById<IV>(R.id.iv).setImageResource(R.mipmap.header3) }
        )

        val list2 = listOf(
                inf(R.layout.snaphelper_recycle_item_alpha).apply {findViewById<IV>(R.id.iv).setImageResource(R.mipmap.header1)  },
                inf(R.layout.snaphelper_recycle_item_alpha).apply { findViewById<IV>(R.id.iv).setImageResource(R.mipmap.header2) },
                inf(R.layout.snaphelper_recycle_item_alpha).apply { findViewById<IV>(R.id.iv).setImageResource(R.mipmap.header3) }
        )

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
        vp3.setPageTransformer(false, RotatePageTransformer())
        vp4.setPageTransformer(false, GalleryPageTransformer())
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
            Log.d("Tdasdad", " "+position)
            when {
                position < -1.0f -> {
                    page.scaleX = MIN_SCALE
                    page.scaleY = MIN_SCALE
                }
                position <= 0.0f -> {
                    page.alpha = 1.0f
                    page.translationX = 0.0f
                    page.scaleX = 1.0f
                    page.scaleY = 1.0f
                }
                position <= 1.0f -> {
                    page.alpha = 1.0f - position
                    page.translationX = -page.width * position
                    val scale = MIN_SCALE + (1.0f - MIN_SCALE) * (1.0f - position)
                    page.scaleX = scale
                    page.scaleY = scale
                }
                else -> {
                    page.scaleX = MIN_SCALE
                    page.scaleY = MIN_SCALE
                }
            }
        }

    }

}
