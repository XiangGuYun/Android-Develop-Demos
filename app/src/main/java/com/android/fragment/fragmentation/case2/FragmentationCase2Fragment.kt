package com.android.fragment.fragmentation.case2

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.R
import com.kotlinlib.TV
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment
import org.greenrobot.eventbus.EventBus

class FragmentationCase2Fragment:SupportFragment(){

    companion object {
        fun newInstance(): FragmentationCase2Fragment {
            return FragmentationCase2Fragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fragmentation_case1, container, false)
        view.findViewById<TV>(R.id.tvFragment).text = "I am Fragment2"

        return view
    }

    // 设置传给上个Fragment的数据
    fun setResult(){
        setFragmentResult(SupportActivity.RESULT_OK, Bundle().apply { putString("msg", "来自第二个Fragment的信息") })
    }

    override fun onBackPressedSupport(): Boolean {
        setResult()
        return super.onBackPressedSupport()
    }

    override fun onSupportVisible() {
        super.onSupportVisible()
        EventBus.getDefault().post(Message.obtain().apply {
            what = 0x112233
            obj = "FragmentationCase2Fragment可见了"
        })
    }

    override fun onSupportInvisible() {
        super.onSupportInvisible()
        EventBus.getDefault().post(Message.obtain().apply {
            what = 0x112234
            obj = "FragmentationCase2Fragment隐藏了"
        })
    }

}
