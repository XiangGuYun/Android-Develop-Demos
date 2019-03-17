package com.android.fragment.fragmentation.case4

import android.os.Bundle
import com.android.R
import com.android.fragment.fragmentation.case1.FragmentationCase1Fragment
import com.android.fragment.fragmentation.case2.FragmentationCase2Fragment
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_fragmentation_case4.*
import me.yokeyword.fragmentation.SupportFragment
import me.yokeyword.fragmentation.SupportHelper

@LayoutId(R.layout.activity_fragmentation_case4)
class FragmentationCase4Activity : KotlinActivity() {

    companion object {
        val RequestCode: Int = 0x123
    }

    private lateinit var supportFragment: FragmentationCase1Fragment

    override fun init(bundle: Bundle?) {

        supportFragment = FragmentationCase1Fragment.newInstance()

        if (findFragment(FragmentationCase1Fragment::class.java) == null) {
            // 装载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
            //loadRootFragment(R.id.fl_container, FragmentationCase2Fragment.newInstance())
            loadRootFragment(R.id.fl_container, supportFragment, true, false)  // 加载根Fragment
        }

        btnViewStack.click {
            // 查看栈视图Dialog
            SupportHelper.showFragmentStackHierarchyView(this)
            // 打印查看栈视图Log
            SupportHelper.logFragmentStackHierarchy(this, "STACK_LOG")
//            // 根据class/tag寻找Fragment
//            SupportHelper.findFragment(fragmentManager, tag/class)
        }

        val list = listOf(
                "start(fragment)",
                "start(fragment,launchMode)",
                "startForResult(fragment,requestCode)",
                "startWithPop(fragment)",
                "startWithPopTo(fragment, targetFragment, includeTargetFragment)"
        )

        val pvOptions = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
            when(options1){
                0->{
                    start(FragmentationCase2Fragment.newInstance())
                }
                1->{
                    // 在栈内的Fragment以SingleTask模式启动（即在其之上的Fragment会出栈）
                    start(FragmentationCase2Fragment.newInstance(), SupportFragment.SINGLETASK)
                }
                2->{
                    startForResult(FragmentationCase2Fragment.newInstance(),RequestCode)
                }
                3->{
                    startWithPop(FragmentationCase2Fragment.newInstance())
                }
                4->{
                    startWithPopTo(FragmentationCase2Fragment.newInstance(), FragmentationCase1Fragment::class.java, true)
                }
            }
        }).build<String>()
        pvOptions.setPicker(list)

        val pvOptionsCode = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
            when(options1){
                0->{
                    codeDialog.text("""
// 启动新的Fragment，启动者和被启动者是在同一个栈的
start(FragmentationCase2Fragment.newInstance())
                    """.trimIndent())
                }
                1->{
                    codeDialog.text("""
// 以某种启动模式，启动新的Fragment
start(SupportFragment fragment, int launchMode)

// 在栈内的Fragment以SingleTask模式启动（即在其之上的Fragment会出栈）
start(FragmentationCase2Fragment.newInstance(), SupportFragment.SINGLETASK)
                    """.trimIndent())
                }
                2->{
                    codeDialog.text("""
// 启动新的Fragment，并能接收到新Fragment的数据返回
startForResult(SupportFragment fragment,int requestCode)

//第一个Fragment
override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
    super.onFragmentResult(requestCode, resultCode, data)
    if(requestCode == FragmentationCase4Activity.RequestCode&&resultCode == (SupportActivity.RESULT_OK)){
        Toast.makeText(activity, "收到了$'{data?.getString("msg")}", Toast.LENGTH_LONG).show()
    }
}

//第二个Fragment
// 设置传给上个Fragment的数据
fun setResult(){
    setFragmentResult(SupportActivity.RESULT_OK, Bundle().apply { putString("msg", "来自第二个Fragment的信息") })
}

override fun onBackPressedSupport(): Boolean {
    setResult()
    return super.onBackPressedSupport()
}
                    """.trimIndent())
                }
                3->{
                    codeDialog.text("""
// 启动目标Fragment，并关闭当前Fragment
startWithPop(SupportFragment fragment)
                    """.trimIndent())
                }
                4->{
                    codeDialog.text("""
// 启动目标Fragment，并关闭targetFragment之上的Fragments
startWithPopTo(SupportFragment fragment, Class targetFragment, boolean includeTargetFragment)

startWithPopTo(FragmentationCase2Fragment.newInstance(), FragmentationCase1Fragment::class.java, true)
                    """.trimIndent())
                }
            }
        }).build<String>()
        pvOptionsCode.setPicker(list)

        btnLaunch.click {
            pvOptions.show()
        }

        btnViewCode.click {
            pvOptionsCode.show()
        }

        val listPop = listOf("添加新Fragment", "pop()",
                "popTo(targetFragment, includeTargetFragment)",
                "popTo(targetFragment, includeTargetFragment, afterTransaction, animation)")

        val pvOptionsPop = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
            when(options1){
                0->{
                    start(NewFragment.newInstance())
                }
                1->{
                    // 出栈当前Fragment(在当前Fragment所在栈内pop)
                    pop()
                }
                2->{
                    // 出栈targetFragment之上的所有Fragments
                    popTo(NewFragment::class.java, true)
                }
                3->{
                    // 如果想出栈后，紧接着.beginTransaction()开始一个新事务，请使用下面的方法：
// 该方法可以自定义出栈动画，可以让动画看起来更自然，如果对动画无要求，也可以使用popTo() + 事务来执行

                }
            }
        }).build<String>()
        pvOptionsPop.setPicker(listPop)

        btnPop.click {
            pvOptionsPop.show()
        }

        /*
        // 获取所在栈内的栈顶Fragment
        getTopFragment();

        // 获取当前Fragment所在栈内的前一个Fragment
        getPreFragment();

        // 通过class获取所在栈内的某个Fragment
        findFragment(Class fragmentClass);

        下面的方法是SupportFragment才有的，从子栈内查找：
        ````java
        getTopChildFragment();
        findChildFragment(Class fragmentClass);
         */
        val listFind = listOf("getTopFragment()", "findFragment(fragmentClass)")

        val pvOptionsFind = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
            when(options1){
                0->{
                    "顶部的Fragment是${topFragment.javaClass.simpleName}".toast()
                }
                1->{
                    "当前的Fragment是否是FragmentationCase1Fragment\n结论：${findFragment(FragmentationCase1Fragment::class.java)==topFragment}".toast()
                }
            }
        }).build<String>()
        pvOptionsFind.setPicker(listFind)

        btnFind.click {
            pvOptionsFind.show()
        }

    }

}
