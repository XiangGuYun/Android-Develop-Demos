package com.kotlinlib.new_fragment

import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment
import me.yokeyword.fragmentation.anim.FragmentAnimator

/**
 * 基于Fragmentation封装的工具类
 */
class FragmentationUtils<T:SupportFragment> {

    var act:SupportActivity
    var lastShowFragmentPosition = 0//最近显示的Fragment位置
    var fragmentList = ArrayList<T>()

    /**
     * 当只托管一个fragment时，采用此构造方法
     * @param activity SupportActivity实例
     * @param fragment SupportFragment实例
     * @param contentId Int 容器Id
     * @param addToBackStack Boolean 是否加入回退战
     * @param allowAnimation Boolean 是否允许动画
     * @constructor
     */
    constructor(activity: SupportActivity, contentId: Int, fragment: T, addToBackStack:Boolean=true, allowAnimation:Boolean=true) {
        act = activity
        activity.loadRootFragment(contentId, fragment, addToBackStack, allowAnimation)
    }

    /**
     * 当托管多个Fragment时，采用此构造方法
     * @param activity SupportActivity
     * @param contentId Int
     * @param fragments Array<out T>
     * @param showPosition Int 显示第几个Fragment
     * @constructor
     */
    constructor(activity: SupportActivity, contentId: Int, vararg fragments:T, showPosition:Int=0) {
        act = activity
        lastShowFragmentPosition = showPosition
        fragmentList = ArrayList(fragments.toList())
        activity.loadMultipleRootFragment(contentId, showPosition, *fragments)
    }

    /**
     * 切换显示另一个平级的Fragment
     * @param position Int
     */
    fun switch(position: Int){
        act.showHideFragment(fragmentList[position], fragmentList[lastShowFragmentPosition])
        lastShowFragmentPosition = position
    }

    /**
     * 启动目标Fragment
     * @param targetFragment T
     * @param launchMode Int 启动模式
     */
    fun start(targetFragment:T, launchMode:Int=SupportFragment.STANDARD){
        act.start(targetFragment, launchMode)
    }

    /**
     * 启动目标Fragment并接受返回事件
     * ①：原fragment需要重写onFragmentResult()方法，并在此方法内实现接收逻辑。
     * ②：目标fragment需要重写onSupportBackPressed()方法，并调用setFragmentResult()函数传递数据包。
     * @param targetFragment T
     * @param requestCode Int
     */
    fun startForResult(targetFragment:T, requestCode:Int){
        act.startForResult(targetFragment, requestCode)
    }

    /**
     * 启动目标Fragment并将原Fragment弹出栈
     * @param targetFragment T
     */
    fun startWithPop(targetFragment: T){
        act.startWithPop(targetFragment)
    }

    /**
     * 启动目标Fragment，并关闭回退栈中目标Fragment之上的Fragment
     * @param targetFragment T
     * @param targetFragmentClass Class<SupportFragment>
     * @param includeTargetFragment Boolean 是否包含目标fragment
     */
    fun startWithPopTo(targetFragment: T, targetFragmentClass:Class<SupportFragment>, includeTargetFragment:Boolean){
        act.startWithPopTo(targetFragment, targetFragmentClass, includeTargetFragment)
    }

    /**
     * 获取回退栈中最上层的Fragment实例
     * @return ISupportFragment?
     */
    fun getTopFragment(): ISupportFragment? {
        return act.topFragment
    }

    /**
     * 通过传入Fragment的Class来获取Fragment的实例，可用来判断当前的Fragment是否是属于特定的子类型
     * @param fragmentClass Class<SupportFragment>
     * @return SupportFragment?
     */
    fun getFragmentByClass(fragmentClass:Class<SupportFragment>): SupportFragment? {
        return act.findFragment(fragmentClass)
    }

    /**
     * 在Activity中统一设置Fragment启动和退出动画，优先级最低
     * @param fa FragmentAnimator
     */
    fun setAnimator(fa:FragmentAnimator){
        act.fragmentAnimator = fa
    }


}