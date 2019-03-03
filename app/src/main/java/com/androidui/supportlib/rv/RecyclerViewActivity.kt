package com.androidui.supportlib.rv

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.*
import android.view.View
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_recycler_view.*

@LayoutId(R.layout.activity_recycler_view)
class RecyclerViewActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        btnViewResult0.click {  go(RVTest0Activity::class.java) }
        btnViewResultHL.click {  go(HLActivity::class.java) }
        btnViewResult1.click {  go(RVTest1Activity::class.java) }
        btnViewResult2.click {  go(RVTest2Activity::class.java) }
        btnViewResult3.click {  go(RVTest3Activity::class.java) }
        btnViewResult4.click {  go(RVTest4Activity::class.java) }
        btnMoreSnap.click { go(MoreSnapActivity::class.java) }

        header0.setLeftClick {  }.setRightClick {
            codeDialog.text("""
RVUtils(rv1)//传入RecyclerView对象
    .rvAdapterHF(testData, //数据源
            R.layout.header1, //HeaderView布局
            {
                headerHolder ->
                headerHolder.text(R.id.tvHeader, "Header")//处理HeaderView
            },
            R.layout.header1,
            {
                footerHolder ->
                footerHolder.text(R.id.tvHeader, "Footer")//处理HeaderView
            },
            {
                normalHolder, pos ->
                normalHolder.setImageResource(R.id.ivCell, testData[pos])//处理普通ItemView
            },
            {
                0 //返回普通ItemView的布局下标，从0开始
            }, R.layout.iv_item)//普通ItemView布局
            """.trimIndent())
        }

        headerHL.setRightClick {
            codeDialog.text("""
RVUtils(rvHL)
    .managerHorizontal()//横向布局
    .decorate(false)
    .rvAdapterHF(testData, //数据源
            R.layout.header2, //HeaderView布局
            {
                headerHolder ->
                headerHolder.text(R.id.tvHeader, "Header\n开 始")//处理HeaderView
            },
            R.layout.header2,
            {
                footerHolder ->
                footerHolder.text(R.id.tvHeader, "Footer\n结 束")//处理HeaderView
            },
            {
                normalHolder, pos ->
                normalHolder.setImageResource(R.id.ivCell, testData[pos])//处理普通ItemView
            },
            {
                0 //返回普通ItemView的布局下标，从0开始
            }, R.layout.tv_item_h)//普通ItemView布局
            """.trimIndent())
        }

        header1.setRightClick {
            codeDialog.text("""
//准备数据
val data = (0..31).toList().map { "Item${'$'}'it" }
RVUtils(rv1)//传入RecyclerView对象
        .gridManager(2)//设置2格纵向网格布局
        .rvAdapterHF(data, //数据
                R.layout.header1, //HeaderView布局
                {
                    headerHolder ->
                    headerHolder.text(R.id.tvHeader, "Header")//处理HeaderView
                },
                R.layout.header1,
                {
                    footerHolder ->
                    footerHolder.text(R.id.tvHeader, "Footer")//处理HeaderView
                },
                {
                    normalHolder, pos ->
                    normalHolder.text(R.id.tvItem, data[pos])//处理普通ItemView
                },
                {
                    0 //返回普通ItemView的布局下标，从0开始
                }, R.layout.rv_item1)//普通ItemView布局
            """.trimIndent())
        }

        header2.setRightClick {
            codeDialog.text("""
val list = ArrayList<String>()
    for (i in 1..100){
        list.add("ITEM"+(i+1))
    }
    val rvUtils = RVUtils(rv1)
    val heights = ArrayList<Int>()
    for (i in 1..100){
        heights.add((100 + Math.random() * 300).toInt())
    }
    rvUtils.staggerManager(4,true)
            .animator(null)
            .enableDraggableItem(list,true)
            .rvAdapter(list, { holder, pos ->
                holder.tv(R.id.tv_cell).let {
                    it.layoutParams.height = heights[pos]
                    it.setBackgroundColor("#${getRandColorCode()}".color())
                }
                holder.text(R.id.tv_cell, list[pos])
                        .itemClick { rvUtils.doDelete(pos, list.size) }
                        .itemLongClick {
                            if(pos!=0) rvUtils.getmItemTouchHelper().startDrag(holder)
                        }
            },R.layout.stagger_cell)
            """.trimIndent())
        }

        header3.setRightClick {
            codeDialog.text("""
class SlideLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr){

    private var dragHelper: ViewDragHelper?=null
    var currLeft = 0

    init {
        dragHelper = ViewDragHelper.create( this, object :ViewDragHelper.Callback(){

            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return child.tag == "drag"
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                currLeft = left
                val childWidth = DensityUtils.dip2px(context, -60f)
                if(left< childWidth){
                    return childWidth//禁止超过左边界
                }
                if(left>width-child.width-childWidth){
                    return width-child.width-childWidth//禁止超过右边界
                }
                return left
            }

            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                if (releasedChild.tag == "drag"&&currLeft>0)
                {
                    dragHelper?.settleCapturedViewAt(0, 0)
                    invalidate()
                }
            }

        })
    }

    override fun computeScroll() {
        if (dragHelper?.continueSettling(true)!!)
        {
            invalidate()
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return dragHelper?.shouldInterceptTouchEvent(ev!!)!!
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        dragHelper?.processTouchEvent(event!!)
        return true
    }



}
            """.trimIndent())
        }

        val testData = listOf(R.mipmap.img1, R.mipmap.header1, R.mipmap.img3, R.mipmap.bg_girl, R.mipmap.header1,
                R.mipmap.header2,R.mipmap.header3,R.mipmap.header5,R.mipmap.header6,R.mipmap.header7)
        RVUtils(rvViewPager)
                .managerHorizontal()
                .snapLinear()
                .rvAdapter(testData,{
                    holder, pos ->
                    holder.setImageResource(R.id.iv, testData[pos])
                },R.layout.snaphelper_recycle_item)

        RVUtils(rvViewPager1)
                .managerHorizontal()
                .snapPager()
                .rvAdapter(testData,{
                    holder, pos ->
                    holder.setImageResource(R.id.iv, testData[pos])
                },R.layout.snaphelper_recycle_item)

        btnViewAnim.click {
            go(RVAnimActivity::class.java)
        }

        val rvUtils = RVUtils(rvViewPager2)
                .managerHorizontal()
                .snapPager()
                .rvAdapter(testData,{
                    holder, pos ->
                    holder.setImageResource(R.id.iv, testData[pos])
                },R.layout.snaphelper_recycle_item)

        val manager = rvViewPager2.layoutManager as LinearLayoutManager
        var move = 0

        rvViewPager2.addOnScrollListener(object :RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                /*
                #SCROLL_STATE_IDLE 0
                #SCROLL_STATE_DRAGGING 1
                #SCROLL_STATE_SETTLING 2
                 */
                when(newState){
                    SCROLL_STATE_IDLE->{
                        val snapView = rvUtils.pagerHelper.findSnapView(manager)
                        snapView?.apply {
                            val anim1 = ObjectAnimator.ofFloat(snapView, "scaleX", 1f, 1.1f,1f)
                            anim1.duration = 1500
                            val anim2 = ObjectAnimator.ofFloat(snapView, "scaleY", 1f, 1.1f,1f)
                            anim2.duration = 1500
                            anim1.start()
                            anim2.start()
//                            val snapPosition = rvViewPager2.getChildAdapterPosition(snapView)
//                            v(R.id.flIv).animate().apply {
//                                scaleX(1.1f).setDuration(500).scaleX(1.0f).setDuration(500)
//                                scaleY(1.1f).setDuration(500).scaleY(1.0f).setDuration(500)
//                            }
//                        val snapPosition = rvUtils.pagerHelper.findTargetSnapPosition(manager,0,0)
                        }
                    }
                    SCROLL_STATE_DRAGGING->{
                    }
                    SCROLL_STATE_SETTLING->{

                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                move += dx
            }
        })

        btnStudy1.click {
            go(StudyRVActivity::class.java)
        }

    }
}
