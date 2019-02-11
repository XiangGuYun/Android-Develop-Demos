package com.kotlinlib.view

import android.support.v4.view.ViewPager
import android.view.View
import android.annotation.SuppressLint
import android.support.v4.view.PagerAdapter
import android.view.ViewGroup


interface ViewPagerUtils {

    /**
     * 设置缓存的页面个数,默认是 1
     */
    fun ViewPager.offscreenPageLimit(limit:Int): ViewPager {
        offscreenPageLimit = limit
        return this
    }

    /**
     * 跳转到特定的页面
     */
    fun ViewPager.current(itemIndex:Int): ViewPager {
        currentItem = itemIndex
        return this
    }

    /**
     * 设置不同页面之间的间隔
     */
    fun ViewPager.pageMargin(margin:Int): ViewPager {
        pageMargin = margin
        return this
    }

    /**
     * 设置不同页面间隔之间的装饰图也就是 divide ，
     * 要想显示设置的图片，需要同时设置 setPageMargin()
     */
    fun ViewPager.pageMarginDrawable(resId:Int): ViewPager {
        setPageMarginDrawable(resId)
        return this
    }

    /**
     * 设置动画
     */
    fun ViewPager.transformerDepth(): ViewPager {
        setPageTransformer(false, DepthPageTransformer())
        return this
    }

    /**
     * 设置动画
     */
    fun ViewPager.transformerZoomOut(): ViewPager {
        setPageTransformer(false, ZoomOutPageTransformer())
        return this
    }

}

/**
 * FragmentPagerAdapter和FragmentStatePagerAdapter的区别
 * FragmentPagerAdapter：对于t不再需要的 fragment，选择调用 onDetach() 方法，仅销毁视图，并不会销毁 fragment 实例。
 * FragmentStatePagerAdapter：会销毁不再需要的 fragment，当当前事务提交以后，
 * 会彻底的将fragment从当前Activity的FragmentManager中移除，
 * state标明，销毁时，会将其 onSaveInstanceState(Bundle outState) 中的 bundle 信息保存下来，
 * 当用户切换回来，可以通过该 bundle 恢复生成新的 fragment
 * 结论：使用 FragmentStatePagerAdapter 更省内存，但是销毁后新建也是需要时间的。
 * 一般情况下，如果你是制作主页面，就 3、4 个 Tab，那么可以选择使用 FragmentPagerAdapter，
 * 如果你是用于 ViewPager 展示数量特别多的条目时，那么建议使用 FragmentStatePagerAdapter。
 */
class KtPagerAdapter:PagerAdapter(){

    /**
     * 这个方法用于判断是否由对象生成界面，官方建议直接返回 return view == object
     * @return Boolean
     */
    override fun isViewFromObject(view: View, any: Any): Boolean {
        return view == any
    }

    /**
     * 获取当前窗体界面数，也就是数据的个数
     * @return Int
     */
    override fun getCount(): Int {
        return 0
    }

    /**
     * 要显示的页面或需要缓存的页面，会调用这个方法进行布局的初始化
     * @return Any
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
    }

    /**
     * 如果页面不是当前显示的页面也不是要缓存的页面，会调用这个方法，将页面销毁。
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

}

/**
 * 翻页动画类
 * 更多详见https://github.com/ToxicBakery/ViewPagerTransforms
 * vp.setPageTransformer(false,DepthPageTransformer())
 */
class DepthPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        val pageWidth = view.width
        when {
            position < -1 -> // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.alpha = 0f
            position <= 0 -> { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.alpha = 1f
                view.translationX = 0f
                view.scaleX = 1f
                view.scaleY = 1f
            }
            position <= 1 -> { // (0,1]
                // Fade the page out.
                view.alpha = 1 - position
                // Counteract the default slide transition
                view.translationX = pageWidth * -position
                // Scale the page down (between MIN_SCALE and 1)
                val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor
            }
            else -> // (1,+Infinity]
                // This page is way off-screen to the right.
                view.alpha = 0f
        }
    }

    companion object {
        private const val MIN_SCALE = 0.75f
    }
}

/**
 * 翻页动画类
 * vp.setPageTransformer(false,ZoomOutPageTransformer())
 */
class ZoomOutPageTransformer : ViewPager.PageTransformer {
    @SuppressLint("NewApi")
    override fun transformPage(view: View, position: Float) {
        val pageWidth = view.width
        val pageHeight = view.height
        when {
            position < -1 -> // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.alpha = 0f
            position <= 1
                //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
            -> { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                val vertMargin = pageHeight * (1 - scaleFactor) / 2
                val horzMargin = pageWidth * (1 - scaleFactor) / 2
                if (position < 0) {
                    view.translationX = horzMargin - vertMargin / 2
                } else {
                    view.translationX = -horzMargin + vertMargin / 2
                }
                // Scale the page down (between MIN_SCALE and 1)
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor
                // Fade the page relative to its size.
                view.alpha = MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA)
            }
            else -> // (1,+Infinity]
                // This page is way off-screen to the right.
                view.alpha = 0f
        }
    }

    companion object {
        private const val MIN_SCALE = 0.85f
        private const val MIN_ALPHA = 0.5f
    }
}