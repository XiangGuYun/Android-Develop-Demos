package com.android.supportlib.viewpager

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.ToxicBakery.viewpager.transforms.*
import com.android.R
import com.android.supportlib.adapter.Vp1Adapter
import com.android.supportlib.adapter.Vp2Adapter
import com.android.supportlib.viewpager.TestFragment.Companion.KEY_TEST_FRAGMENT
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.kotlinlib.IV
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.fragment.FragPagerEngine
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_view_pager.*


@LayoutId(R.layout.activity_view_pager)
class ViewPagerActivity : KotlinActivity() {

    companion object {
        var MIN_SCALE = 0.45f//数值越小，切换过去的图变化越明显
        val MAX_ROTATION = 20.0f
        val MAX_TRANSLATE = 40.0f//数值越大越容易滑动
    }

    val transformers = arrayListOf("AccordionTransformer","BackgroundToForegroundTransformer",
            "CubeInTransformer","CubeOutTransformer","DefaultTransformer","DepthPageTransformer",
            "DrawerTransformer","FlipHorizontalTransformer","FlipVerticalTransformer","ForegroundToBackgroundTransformer",
            "RotateDownTransformer","RotateUpTransformer","ScaleInOutTransformer","StackTransformer","TabletTransformer",
            "ZoomInTransformer","ZoomOutSlideTransformer","ZoomOutTransformer")

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
        }.setRightClick {
            codeDialog.text("""
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
            """.trimIndent())
        }

        header3.setRightClick {
            codeDialog.text("""

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
            """.trimIndent())
        }

        header4.setRightClick {
            codeDialog.text("""
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

        val engine = FragPagerEngine(this, vpEngine,
                TestFragment().apply { arguments = Bundle().apply {
                    putString(KEY_TEST_FRAGMENT, "fragment1")
                    putInt(TestFragment.KEY_TEST_FRAGMENT_COLOR, Color.parseColor("#ff6666"))
                } },
                TestFragment().apply { arguments = Bundle().apply {
                    putString(KEY_TEST_FRAGMENT, "fragment2")
                    putInt(TestFragment.KEY_TEST_FRAGMENT_COLOR, Color.parseColor("#66ff66"))
                } },
                TestFragment().apply { arguments = Bundle().apply {
                    putString(KEY_TEST_FRAGMENT, "fragment3")
                    putInt(TestFragment.KEY_TEST_FRAGMENT_COLOR, Color.parseColor("#6666ff"))
                } })
        engine.addTabLayout(tlEngine,
                {tab, index -> tab.text = "${index+1}" },
                {tab -> vpEngine.currentItem = tab.position},
                {tab ->  })

        FragPagerEngine(this, vpTransformer,
                TestFragment().apply { arguments = Bundle().apply {
                    putString(KEY_TEST_FRAGMENT, "fragment1")
                    putInt(TestFragment.KEY_TEST_FRAGMENT_COLOR, Color.parseColor("#ff6666"))
                } },
                TestFragment().apply { arguments = Bundle().apply {
                    putString(KEY_TEST_FRAGMENT, "fragment2")
                    putInt(TestFragment.KEY_TEST_FRAGMENT_COLOR, Color.parseColor("#66ff66"))
                } },
                TestFragment().apply { arguments = Bundle().apply {
                    putString(KEY_TEST_FRAGMENT, "fragment3")
                    putInt(TestFragment.KEY_TEST_FRAGMENT_COLOR, Color.parseColor("#6666ff"))
                } })

        /*
        "ABaseTransformer","AccordionTransformer","BackgroundToForegroundTransformer",
            "BuildConfig","CubeInTransformer","CubeOutTransformer","DefaultTransformer","DepthPageTransformer",
            "DrawerTransformer","FlipHorizontalTransformer","FlipVerticalTransformer","ForegroundToBackgroundTransformer",
            "RotateDownTransformer","RotateUpTransformer","ScaleInOutTransformer","StackTransformer","TabletTransformer",
            "ZoomInTransformer","ZoomOutSlideTransformer","ZoomOutTransformer"
         */
        val pvOptions = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
            vpTransformer.setPageTransformer(false, when(options1){
                0-> AccordionTransformer()
                1-> BackgroundToForegroundTransformer()
                2-> CubeInTransformer()
                3-> CubeOutTransformer()
                4-> DefaultTransformer()
                5-> DepthPageTransformer()
                6-> DrawerTransformer()
                7-> FlipHorizontalTransformer()
                8-> FlipVerticalTransformer()
                9-> ForegroundToBackgroundTransformer()
                10-> RotateDownTransformer()
                11-> RotateUpTransformer()
                12-> ScaleInOutTransformer()
                13-> StackTransformer()
                14-> TabletTransformer()
                15-> ZoomInTransformer()
                16-> ZoomOutSlideTransformer()
                else-> ZoomOutTransformer()
            })
        }).build<String>()
        pvOptions.setPicker(transformers.toMutableList())

        btnTransformer.click {
            pvOptions.show()
        }

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
            when {
                position < -1 -> rotate(page, -MAX_ROTATION)
                position <= 1 -> rotate(page, MAX_ROTATION * position)
                else -> rotate(page, MAX_ROTATION)
            }
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
