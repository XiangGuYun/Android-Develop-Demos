package com.android.supportlib.recyclerview

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.*
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import com.kotlinlib.view.recyclerview.RVUtils
import kotlinx.android.synthetic.main.activity_study_rv.*

@LayoutId(R.layout.activity_study_rv)
class StudyRVActivity : KotlinActivity() {

    val testData = listOf(R.mipmap.img1, R.mipmap.header1, R.mipmap.img3, R.mipmap.bg_girl, R.mipmap.header1,
            R.mipmap.header2,R.mipmap.header3,R.mipmap.header5,R.mipmap.header6,R.mipmap.header7)

    override fun init(bundle: Bundle?) {
        val rvUtils = RVUtils(rvStudy)
                .managerHorizontal()
                .snapPager()
                .rvAdapter(testData,{
                    holder, pos ->
                    holder.setImageResource(R.id.iv, testData[pos])
                },R.layout.snaphelper_recycle_item3)

        val manager = rvStudy.layoutManager as LinearLayoutManager
        var move = 0

        rvStudy.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                /*
                #SCROLL_STATE_IDLE 0
                #SCROLL_STATE_DRAGGING 1
                #SCROLL_STATE_SETTLING 2
                 */
                when(newState){
                    SCROLL_STATE_IDLE->{
                        val snapView = rvUtils.pagerHelper.findSnapView(manager)
//                        val snapPosition = rvUtils.pagerHelper.findTargetSnapPosition(manager,0,0)
                    }
                    SCROLL_STATE_DRAGGING->{
                    }
                    SCROLL_STATE_SETTLING->{

                    }
                }
            }

            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                move += dx
                tv1.text = "滑动方向：${when(true){
                        dx==0->"静止"
                        dx<0->"左滑"
                        else->"右滑"
                    }
                }"

//                rvStudy.getChildViewHolder(rvStudy.getChildAt(manager.findFirstVisibleItemPosition())).setIsRecyclable(false)
//                rvStudy.getChildViewHolder(rvStudy.getChildAt(manager.findFirstCompletelyVisibleItemPosition())).setIsRecyclable(false)
//                rvStudy.getChildViewHolder(rvStudy.getChildAt(manager.findLastVisibleItemPosition())).setIsRecyclable(false)
//                rvStudy.getChildViewHolder(rvStudy.getChildAt(manager.findLastCompletelyVisibleItemPosition())).setIsRecyclable(false)

                tv2.text = "滑动距离：$move"
                tv3.text = "首个可见：${manager.findFirstVisibleItemPosition()} 为空吗？${rv.getChildAt(manager.findFirstVisibleItemPosition())==null}"
                tv4.text = "首个全可见：${manager.findFirstCompletelyVisibleItemPosition()} 为空吗？${rv.child(manager.findFirstCompletelyVisibleItemPosition())==null}"
                tv5.text = "末个可见：${manager.findLastVisibleItemPosition()} 为空吗？${rv.child(manager.findLastVisibleItemPosition())==null}"
                tv6.text = "末个全可见：${manager.findLastCompletelyVisibleItemPosition()} 为空吗？${rv.child(manager.findLastCompletelyVisibleItemPosition())==null}"
                tv7.text = "当前吸附的View：${rv.getChildAdapterPosition(rvUtils.pagerHelper.findSnapView(manager)!!)}"
                rvUtils.pagerHelper.findSnapView(manager)?.v(R.id.flIv)?.animate()?.apply {
                    scaleX(1.2f).duration = 500
                    scaleY(1.2f).duration = 500
                }

            }

        })

    }

}
