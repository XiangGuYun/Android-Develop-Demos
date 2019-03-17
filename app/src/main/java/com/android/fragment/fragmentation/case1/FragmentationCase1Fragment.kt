package com.android.fragment.fragmentation.case1

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.R
import com.android.fragment.fragmentation.case4.FragmentationCase4Activity
import com.kotlinlib.TV
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment
import me.yokeyword.fragmentation.anim.FragmentAnimator
import org.greenrobot.eventbus.EventBus

class FragmentationCase1Fragment:SupportFragment(){

    companion object {
        fun newInstance(): FragmentationCase1Fragment {
            return FragmentationCase1Fragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fragmentation_case1, container, false)
        view.findViewById<TV>(R.id.tvFragment).text = "I am Fragment1"
        return view
    }

    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        super.onFragmentResult(requestCode, resultCode, data)
        if(requestCode == FragmentationCase4Activity.RequestCode&&resultCode == (SupportActivity.RESULT_OK)){
            Toast.makeText(activity, "收到了${data?.getString("msg")}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSupportVisible() {
        super.onSupportVisible()
        EventBus.getDefault().post(Message.obtain().apply {
            what = 0x112233
            obj = "FragmentationCase1Fragment可见了"
        })
    }

    override fun onSupportInvisible() {
        super.onSupportInvisible()
        EventBus.getDefault().post(Message.obtain().apply {
            what = 0x112234
            obj = "FragmentationCase1Fragment隐藏了"
        })
    }

}
