package com.android.fragment.fragmentation

import android.os.Bundle
import com.android.R
import com.android.common.dialog.ListBtmDialog
import com.android.fragment.fragmentation.case1.FragmentationCase1Activity
import com.android.fragment.fragmentation.case2.FragmentationCase2Activity
import com.android.fragment.fragmentation.case3.FragmentationCase3Activity
import com.android.fragment.fragmentation.case4.FragmentationCase4Activity
import com.android.fragment.fragmentation.case5.FragmentationCase5Activity
import com.android.fragment.fragmentation.case6.FragmentationCase6Activity
import com.android.fragment.fragmentation.case7.FragmentationCase7Activity
import com.android.fragment.fragmentation.case8.FragmentationCase8Activity
import com.android.fragment.fragmentation.case9.FragmentationCase9Activity
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_fragmentation.*

@LayoutId(R.layout.activity_fragmentation)
class FragmentationActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        initHeader()

        initBtn()

    }

    private fun initBtn() {
        btn1.click { go(FragmentationCase1Activity::class.java) }

        btn2.click { go(FragmentationCase2Activity::class.java) }

        btn3.click { go(FragmentationCase3Activity::class.java) }

        btn4.click { go(FragmentationCase4Activity::class.java) }

        btn5.click { go(FragmentationCase5Activity::class.java) }

        btn6.click {
            ListBtmDialog(this,"请选择", arrayListOf(
                    "无懒加载效果","有懒加载效果","在动画结束后初始化Fragment"
            )){
                dialog, view, pos ->
                when(pos){
                    0->{
                        view.click {
                            go(FragmentationCase6Activity::class.java,"type" to 1)
                            dialog.dismiss()
                        }
                    }
                    1->{
                        view.click {
                            go(FragmentationCase6Activity::class.java,"type" to 2)
                            dialog.dismiss()
                        }
                    }
                    2->{
                        view.click {
                            go(FragmentationCase6Activity::class.java,"type" to 3)
                            dialog.dismiss()
                        }
                    }
                }

            }.show()
        }

        btn7.click { go(FragmentationCase7Activity::class.java) }

        btn8.click { go(FragmentationCase8Activity::class.java) }

        btn9.click { go(FragmentationCase9Activity::class.java) }
    }

    private fun initHeader() {
        header1
                .setLeftClick {
                    codeDialog.text("""

                    """.trimIndent())
                }
                .setRightClick {
                    codeDialog.text("""
/**
Application中
*/
// 栈视图等功能，建议在Application里初始化
Fragmentation.builder()
        // 显示悬浮球 ; 其他Mode:SHAKE: 摇一摇唤出   NONE：隐藏
        .stackViewMode(Fragmentation.BUBBLE)
        .debug(BuildConfig.DEBUG).install()

/**
FragmentationCase1Activity(需要继承SupportActivity)中
*/
if (findFragment(FragmentationCase1Fragment::class.java) == null) {
    loadRootFragment(R.id.fl_container, FragmentationCase1Fragment.newInstance())  // 加载根Fragment
}

/**
FragmentationCase1Fragment(需要继承SupportFragment)中
*/
companion object {
    fun newInstance(): FragmentationCase1Fragment {
        return FragmentationCase1Fragment()
    }
}

override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    val view = inflater.inflate(R.layout.fragment_fragmentation_case1, container, false)

    return view
}
            """.trimIndent())
                }

        header2.setLeftClick {
            codeDialog.text("""
前言：核心概括
loadRootX()系列方法，操作的对象是 孩子Fragment，为避免被强杀重启后重复load，建议在findChildFragment(ChildFragment.class)==null情况下才load；
startX()，popX()，find/getX()系列方法，操作的对象是 兄弟Fragment；
popChildX()，find/getChildX()系列方法，操作的对象是 孩子Fragment。
通过上面3个概念配合详细的API以及栈视图，就可以轻松开发出单Activity＋多Fragment结构的App。

Application初始化API
Fragmentation.builder()
    // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
    .stackViewMode(Fragmentation.BUBBLE)
    .debug(BuildConfig.DEBUG)
    // 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
    .handleException(new ExceptionHandler() {
         @Override
         public void onException(Exception e) {
             // 建议在该回调处上传至我们的Crash监测服务器
             // 以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
             // Bugtags.sendException(e);
         }
     })
     .install();

SupportActivity独有API
// 当Fragment根布局没有设定background属性时,
// Fragmentation默认使用Theme的android:windowbackground作为Fragment的背景,
// 可以通过该方法改变Fragment背景。
setDefaultFragmentBackground(@DrawableRes int backgroundRes);
SupportHelper - API
// 查看栈视图Dialog
SupportHelper.showFragmentStackHierarchyView();
// 打印查看栈视图Log
SupportHelper.logFragmentStackHierarchy(TAG);
// 根据class/tag寻找Fragment
SupportHelper.findFragment(fragmentManager, tag/class)

...
API一览(除了特别标注的API，对于其他API，SupportActivity和SupportFragment都具有)
1、装载根Fragment，一般在findChildFragment(ChildFragment.class)==null时load

// 装载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
loadRootFragment(int containerId, SupportFragment toFragment)
loadRootFragment(int containerId, SupportFragment toFragment, boolean addToBackStack, boolean allowEnterAnim)

// 装载多个根Fragment，用于同级Fragment的场景，详情见新Demo的MainActivity
loadMultipleRootFragment(int containerId, int showPosition, SupportFragment... toFragments);
附：同级Fragment场景下的切换

// show一个Fragment，hide一个Fragment； 主要用于类似微信主页那种 切换tab的情况
showHideFragment(SupportFragment showFragment, SupportFragment hideFragment);
2、启动Fragment

如果在Activity使用，则本质是activity.getSupportFragmentManager().getTopFragment().start(f)；

// 启动新的Fragment，启动者和被启动者是在同一个栈的
start(SupportFragment fragment)

// 以某种启动模式，启动新的Fragment
start(SupportFragment fragment, int launchMode)

// 启动新的Fragment，并能接收到新Fragment的数据返回
startForResult(SupportFragment fragment,int requestCode)

// 启动目标Fragment，并关闭当前Fragment
startWithPop(SupportFragment fragment)

// 启动目标Fragment，并关闭targetFragment之上的Fragments
startWithPopTo(SupportFragment fragment, Class targetFragment, boolean includeTargetFragment)


// 1.0.0 New:  你可以使用extraTransaction() + start() 来实现上面的各种startXX()设置更多功能
supportFragment.extraTransaction()
                .setTag(tag)  // 自定义tag
                .addSharedElement(xx).setLaunchMode(SINGLETASK).withPop(true).forResult(1)
                .start()
                .popTo(tag, includeTagFragment)
              //.dontAddToBackStack()
              //.add()
              //.remove(f) ...
下面的方法是SupportFragment才有的：

// replace方式启动目标Fragment，配合replaceLoadRootFragment()使用
replaceFragment(SupportFragment toFragment, boolean addToBack)

// Fragmentation的事务内部是通过一个ActionQueue队列排队执行的，使用该方法可以将自定义任务（事务）入队执行
post(Runnable runnable)
3、出栈

// 出栈当前Fragment(在当前Fragment所在栈内pop)
pop();

// 出栈targetFragment之上的所有Fragments
popTo(Class targetFragment, boolean includeTargetFragment);

// 如果想出栈后，紧接着.beginTransaction()开始一个新事务，请使用下面的方法：
// 该方法可以自定义出栈动画，可以让动画看起来更自然，如果对动画无要求，也可以使用popTo() + 事务来执行
popTo(Class targetFragment, boolean includeTargetFragment, Runnable afterTransaction, int animation)
下面的方法是SupportFragment才有的，操作目标是子Fragment：

popChild();
popToChild(Class fragmentClass, boolean includeSelf);
popToChild(Class fragmentClass, boolean includeSelf, Runnable afterTransaction)
popToChild(Class fragmentClass, boolean includeSelf, Runnable afterTransaction,int popAnim)
4、查找Fragment

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
6、输入法相关

因为Fragment被出栈时，不会自动隐藏软键盘，以及弹出软键盘有些麻烦，故提供下面2个方法

// 隐藏软键盘 一般用在hide时
hideSoftInput();

// 显示软键盘，调用该方法后，会在onPause时自动隐藏软键盘
showSoftInput(View view);
            """.trimIndent())
        }

        header3.setLeftClick {
            codeDialog.text("""
onSupportVisible()， onSupportInvisible()
onSupportVisible()等生命周期调用顺序：onActivityCreated() -> onResume() -> onSupportVisible -> onLazyInitView() => onSupportInvisible() -> onPause()

public class MyFragment extends SupportFragment{
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        // todo,当该Fragment对用户可见时
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        // todo,当该Fragment对用户不可见时
    }
}
这2个方法会在Fragment可见时回调，即：只要该Fragment对用户可见时，即会被调用，包括嵌套的子Fragment。

比如A内有个子FragmentB，B通过A startFragment(C) （此时A和C是同级Fragment），当你pop(C)时，A和B的onSupportVisible()都会被回调。

我相信这2个方法在单Activity多Fragment等嵌套较多的结构上，开发过程会方便很多！
            """.trimIndent())
        }

        header4.setLeftClick {
            codeDialog.text("""
懒加载
在使用Fragment过程中，有些场景需要懒加载，比如FragmentAdapter的懒加载、同级Fragment切换的懒加载，库中自0.8版本提供了onLazyInitView(Bundle saveInstanceState)方法使用。

public class LazyFragment extends BaseFragment {
    /**
     * 懒加载
     */
    public void onLazyInitView(@Nullable Bundle savedInstanceState){
        // todo
    }
}
优化Animation性能
在复杂Fragment页面，第一次start时，会导致该Fragment因复杂初始化和动画的同时进行，导致动画卡顿问题，库中提供一个解决方案：onEnterAnimationEnd(Bundle saveInstanceState)的回调方法。

该方法会在转场动画结束后调用，如果没有动画则在onActivityCreated时调用，此时在onEnterAnimtionEnd(Bundle saveInstanceState)里初始化复杂数据，可以避免保证Fragment动画的流畅。

public View onCreateView(...) {
    ...
    // 这里仅给toolbar设置标题，返回箭头等轻量UI的操作
    initView();
    return view;
}

@Override
protected void onEnterAnimationEnd(Bundle saveInstanceState) {
     // 这里设置Listener、各种Adapter、请求数据等等
    initLazyView();
}
此外，竖直动画在视觉上会比横向动画更流畅
            """.trimIndent())
        }

        header5.setLeftClick {
            codeDialog.text("""
Fragmentation库对Fragment的转场动画设定有更简洁的实现，库中内置提供了2种动画：横向和竖向动画，默认竖向的转场动画。

1. 通过SupportActivity设置全局Fragment的转场动画
当然如果不满足你的需求，可以自定义。你只需要通过复写SupportActivity的onCreateFragmentAnimator()，就可以轻松为该Activity下所有的Fragment设置转场动画，或者使用setFragmentAnimator()来动态改变动画。

@Override
protected FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        // return new DefaultHorizontalAnimator();
        // 设置无动画
        // return new DefaultNoAnimator();
        // 设置自定义动画
        // return new FragmentAnimator(enter,exit,popEnter,popExit);

        // 默认竖向(和安卓5.0以上的动画相同)
        return super.onCreateFragmentAnimator();
}
2. 为Fragment单独设置转场动画
如果某个Fragment不想使用Activity全局设置的动画，则可以通过复写Fragment内的onCreateFragmentAnimator()仅为该Fragment设置动画，，或者使用setFragmentAnimator()来动态改变动画。

// 通过
setFragmentAnimator(FragmentAnimator);

// 或者复写
@Override
protected FragmentAnimator onCreateFragmentAnimator() {
    // 获取在SupportActivity里设置的全局动画对象，进行修改
    FragmentAnimator fragmentAnimator = super.onCreateFragmentAnimation();
    fragmentAnimator.setEnter(0);
    fragmentAnimator.setExit(0);
    return fragmentAnimator;

    // 也可以直接通过
    // return new FragmentAnimator(enter,exit,popEnter,popExit)设置一个全新的动画
}
3. 为启动方Fragment和目标方Fragment 单独设置转场动画
下面的方法可以让启动的Fragment和目标Fragment拥有临时动画，优先级大于上面的FragmentAnimator设置；

场景：当你想以某个特殊动画启动一个Fragment的小弹窗时，可以使用下面方法，它不影响Fragment本身的FragmentAnimator

见Demo

extraTransaction()
   .setCustomAnimations(targetFragmentEnter, currentFragmentPopExit, currentFragmentPopEnter, targetFragmentExit)
   .start(targetFragment);
4. MD过渡动画：SharedElement
Fragmentation支持SharedElement（5.0+），示例代码如下：

if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
     setExitTransition(new Fade());
     fragment.setEnterTransition(new Fade());
     fragment.setSharedElementReturnTransition(new DetailTransition());
     fragment.setSharedElementEnterTransition(new DetailTransition());
     // 25.1.0以下的support包,Material过渡动画只有在进栈时有,返回时没有;
     // 25.1.0+的support包，SharedElement正常
     fragment.extraTransaction()
             .addSharedElement(((FirstHomeAdapter.VH) vh).img, getString(R.string.image_transition))
             .addSharedElement(((FirstHomeAdapter.VH) vh).tvTitle, "tv")
             .start();
} else{
    start(fragment);
}
4. 动画的优化

在复杂Fragment页面，第一次start时，会导致该Fragment因复杂初始化和动画的同时进行，导致动画卡顿问题，库中提供一个解决方案：onEnterAnimationEnd()的回调方法，具体使用移步使用场景－ Fragment的优化使用            """.trimIndent())
        }

        header6.setLeftClick {
            codeDialog.text("""
Fragmentation库提供一个类似Android事件分发机制的Back键监听机制：

按下Back键，事件首先传递到Activity内栈顶的Fragment，如果该Fragment有子Fragment，则传递到子栈内的栈顶子Fragment，依次类推；

如果栈顶子Fragment不处理该事件，则向上传递（栈底还有子Fragment则向栈底传递，如果没有则向父Fragment传递，最终到SupportActivity）

如果处理该事件，则消费该事件，不再向上传递。

对于SupportActivity
注意：请不要复写onBackPressed()方法，改为复写onBackPressedSupport()；

// 任意Fragment的onBackPressedSupport()返回true，该方法都不会被回调
@Override
public void onBackPressedSupport() {
    // super的实现为：
    // if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
    //     mFragmentation.back(getSupportFragmentManager());
    // } else {
    //     finish();
    // }
    super.onBackPressedSupport();
}
对于SupportFragment
如果return true，则消费该事件，不再向上传递。

@Override
public boolean onBackPressedSupport() {
    // 默认flase，继续向上传递
    return super.onBackPressedSupport();
}
            """.trimIndent())
        }

        header7.setLeftClick {
            codeDialog.text("""
同级未加入回退栈的Fragments
ViewPager、FragmentTabHost、及库提供的loadMultiRootFragment()均属于这种情况

记住一个原则： 避免在未加入回退栈的层级内去start()其它Fragment， 比如ViewPager、loadMultiRootFragment()、FragmentTabHost内部的Fragment不可以直接start，这种情况下其内的栈应该保持干净，如果想启动其它Fragment，应该由它们的父Fragment或子Fragment去start

装载TabFragments
装载同级Fragment，我们需要用到loadMultipleRootFragment(int containerId, int showPosition, SupportFragment... fragments)，其中参数分别对应容器id，需要show的Fragment下标位置，SupportFragment的数组。

public class MainActivity extends SupportActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_activity_main);

        if (findFragment(MultiFirstFragment.class) == null) {
            mFragments[FIRST] = MultiFirstFragment.newInstance();
            mFragments[SECOND] = MultiSecondFragment.newInstance();
            mFragments[THIRD] = MultiThirdFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]
        } else {
            // 这里库已经做了Fragment恢复工作，不需要额外的处理
            // 这里我们需要拿到mFragments的引用，用下面的方法查找更方便些，也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些)
            mFragments[FIRST] = findFragment(MultiFirstFragment.class);
            mFragments[SECOND] = findFragment(MultiSecondFragment.class);
            mFragments[THIRD] = findFragment(MultiThirdFragment.class);
        }
    }
}
切换TabFragment
// 第一个参数为需要show的Fragment，第二个为需要hide的Fragment
showHideFragment(needShowFragment, needHideFragment);
优化：懒加载
通常我们会使用懒加载来兼顾性能和优化内存，详情查看使用场景－ Fragment的优化使用
            """.trimIndent())
        }
    }

}
