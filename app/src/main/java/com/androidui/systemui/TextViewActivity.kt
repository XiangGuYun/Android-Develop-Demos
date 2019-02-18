package com.androidui.systemui

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_text_view.*


@LayoutId(R.layout.activity_text_view)
class TextViewActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        /*
        字间距属性
         */
        tvTextSpacing.textScaleX = 2.0f

        headerSpacing.setRightClick {
            codeDialog.text("""
                tvTextSpacing.textScaleX = 2.0f

                 <TextView
                    android:id="@+id/tvTextSpacing"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:padding="10dp"
                    android:text="改变了字间距的文字"/>

                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:padding="10dp"
                    android:lineSpacingExtra="10dp"
                    android:text="行间距为10dp的文字1测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测"/>

                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:padding="10dp"
                    android:lineSpacingMultiplier="2.5"
                    android:text="行间距为2.5倍的文字2测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测"/>
            """.trimIndent())
        }

        /*
        链接属性
         */
        headerLink.setRightClick {
            codeDialog.text("""
                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:gravity="center"
                    android:paddingTop="10dp"
                    android:paddingBottom="10dp"
                    android:text="http://www.baidu.com"
                    android:autoLink="web"
                    />
            """.trimIndent())
        }

        /*
        单行属性
         */
        headerSingleLine.setRightClick {
            codeDialog.text("""
                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:padding="10dp"
                    android:text="常温区哦草擦我额额啊是的但是打算打算撒打算打算打扫打扫打扫打扫的撒打死打伤"
                    android:singleLine="true"
                    />
            """.trimIndent())
        }

        /*
        行数属性
         */
        headerLines.setRightClick {
            codeDialog.text("""
                <!--lines表示不管文本大小都显示指定行数-->
                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:padding="10dp"
                    android:text="饮料瓶身印着美女模特图。"
                    android:lines="2"
                    />

                <!--maxLines表示最多显示指定行数-->
                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:padding="10dp"
                    android:text="饮料瓶身印着美女模特图，加上“我从小喝到大”这句惹人遐想的广告语，最近，椰树牌椰汁2019年新包装成了众矢之的，不少网友直斥低俗；丁香医生也发文称“椰汁真不能丰胸”。13日，海口市工商部门宣布对海南椰树集团涉嫌发布低俗、虚假广告的行为进行立案调查。当晚，椰树集团作出回复：广告语的意思是代言模特从小时候就喝椰树椰汁，公司也从没有宣传过椰汁有丰胸效果。"
                    android:maxLines="2"
                    />
            """.trimIndent())
        }

        /*
        省略属性
         */
        headerEllipsize.setRightClick {
            codeDialog.text("""
               <TextView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:padding="10dp"
                android:text="饮料瓶身印着美女模特图，加上“我从小喝到大”这句惹人遐想的广告语，最近，椰树牌椰汁2019年新包装成了众矢之的，不少网友直斥低俗；丁香医生也发文称“椰汁真不能丰胸”。13日，海口市工商部门宣布对海南椰树集团涉嫌发布低俗、虚假广告的行为进行立案调查。当晚，椰树集团作出回复：广告语的意思是代言模特从小时候就喝椰树椰汁，公司也从没有宣传过椰汁有丰胸效果。"
                android:maxLines="2"
                android:ellipsize="end"
                />
            """.trimIndent())
        }

        /*
        自定义字体
         */
        headerFont.setRightClick {
            codeDialog.text("""
                tvCustomFont.typeface = Typeface.createFromAsset(assets, "font/myfont.ttf")
            """.trimIndent())
        }
        tvCustomFont.typeface = Typeface.createFromAsset(assets, "font/myfont.ttf")

        /*
        TextView中使用Html
         */
        headerHtml.setLeftClick {
            codeDialog.text("""
                TextView支持的常用标签
                <font>：设置颜色和字体。
                <big>：设置字体大号
                <small>：设置字体小号
                <i><b>：斜体粗体
                <a>：连接网址
                <img>：图片
            """.trimIndent())
        }

        headerHtml.setRightClick {
            codeDialog.text("""
        var html ="<font color ='red'>Hello android</font><br/>"
        html+="<font color='#0000ff'><big><i>Hello android</i></big></font><p>"
        html+="<big><a href='http://www.baidu.com'>百度</a></big>"

        tvHtml.text = Html.fromHtml(html)
        tvHtml.movementMethod = LinkMovementMethod.getInstance()//这句代码使得链接能被触发
            """.trimIndent())
        }

        var html ="<font color ='red'>Hello android</font><br/>"
        html+="<font color='#0000ff'><big><i>Hello android</i></big></font><p>"
        html+="<big><a href='http://www.baidu.com'>百度</a></big>"

        tvHtml.text = Html.fromHtml(html)
        tvHtml.movementMethod = LinkMovementMethod.getInstance()//这句代码使得链接能被触发

        headerTVPic.setLeftClick {
            codeDialog.text("""
                static Spanned fromHtml(String source,Html.ImageGetter imageGetter,Html.TagHandler tagHandler)
                参数imageGetter：设定<img>标签中的图像资源文件
                参数tagHandler：主要是为了处理Html类无法识别的html标签的情况，一般不会用上，传值为null即可。

                在ImageGetter的回调方法中，会得到一个代表img的src的字符串，需要根据该字符串来返回对应的drawable对象
                该drawable对象仍然必须调用setBounds()
            """.trimIndent())
        }

        headerTVPic.setRightClick {
            codeDialog.text("""
                 tvHtmlImg.text = Html.fromHtml("<img src='image1'/>阿纳多卢通讯社援引警方调查报告报道，" +
                "卡舒吉遭杀害并肢解后可能被投入沙特驻伊斯坦布尔领事馆内一座炉子中焚化。" +
                "那座炉子燃烧温度可超过1000摄氏度，能毁灭尸体的所有脱氧核糖核酸(DNA)痕迹。", Html.ImageGetter {

                    return@ImageGetter when(it){
                        "image1"->{
                            resources.getDrawable(R.mipmap.ic_launcher).apply {
                                setBounds(0, 0, intrinsicWidth, intrinsicHeight)
                            }
                        }
                        else->{
                            resources.getDrawable(R.mipmap.ic_launcher_round)
                        }
                    }

                }, null)
            """.trimIndent())
        }


        tvHtmlImg.text = Html.fromHtml("<img src='image1'/>阿纳多卢通讯社援引警方调查报告报道，" +
                "卡舒吉遭杀害并肢解后可能被投入沙特驻伊斯坦布尔领事馆内一座炉子中焚化。" +
                "那座炉子燃烧温度可超过1000摄氏度，能毁灭尸体的所有脱氧核糖核酸(DNA)痕迹。", Html.ImageGetter {

            return@ImageGetter when(it){
                "image1"->{
                    resources.getDrawable(R.mipmap.icon).apply {
                        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
                    }
                }
                else->{
                    resources.getDrawable(R.mipmap.ic_launcher_round)
                }
            }

        }, null)

        hearPartClick.setLeftClick {
            codeDialog.text("""
SpannableString可供我们使用的API有下面这些：

BackgroundColorSpan 背景色
ClickableSpan 文本可点击，有点击事件
ForegroundColorSpan 文本颜色（前景色）
MaskFilterSpan 修饰效果，如模糊(BlurMaskFilter)、浮雕(EmbossMaskFilter)
MetricAffectingSpan 父类，一般不用
RasterizerSpan 光栅效果
StrikethroughSpan 删除线（中划线）
SuggestionSpan 相当于占位符
UnderlineSpan 下划线
AbsoluteSizeSpan 绝对大小（文本字体）
DynamicDrawableSpan 设置图片，基于文本基线或底部对齐。
ImageSpan 图片
RelativeSizeSpan 相对大小（文本字体）
ReplacementSpan 父类，一般不用
ScaleXSpan 基于x轴缩放
StyleSpan 字体样式：粗体、斜体等
SubscriptSpan 下标（数学公式会用到）
SuperscriptSpan 上标（数学公式会用到）
TextAppearanceSpan 文本外貌（包括字体、大小、样式和颜色）
TypefaceSpan 文本字体
URLSpan 文本超链接
            """.trimIndent())
        }

        /*
        给部分TextView添加点击事件setSpan()
         */
        hearPartClick.setRightClick {
            codeDialog.text("""
                val textTest = "点击我弹出Toast"

                val ss = SpannableString(textTest)

                ss.setSpan(object :ClickableSpan(){
                    override fun onClick(widget: View) {
                        "你成功了".toast()
                    }
                }, 5, textTest.length, Spanned.SPAN_COMPOSING)

                tvPartClick.text = ss

                tvPartClick.movementMethod = LinkMovementMethod.getInstance()
            """.trimIndent())
        }

        val textTest = "点击我弹出Toast"
        val ss = SpannableString(textTest)
        ss.setSpan(object :ClickableSpan(){
            override fun onClick(widget: View) {
                "Toast".toast()
            }
        }, 5, textTest.length, Spanned.SPAN_COMPOSING)
        tvPartClick.text = ss
        tvPartClick.movementMethod = LinkMovementMethod.getInstance()

        val textTest1 = "张三，李四，王五，赵六，黄奇，孙吧觉得很赞"
        val ssb = SpannableStringBuilder()
        val strList = textTest1.split("，")
        strList.forEachIndexed {i,it->
            val ss = SpannableString(if(i!=strList.size-1) "$it，" else it)
            ss.setSpan(object :ClickableSpan(){
                override fun onClick(widget: View) {
                    it.toast()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = Color.BLUE
                    ds.isUnderlineText = false
                }
            },0, if(i!=strList.size-1) it.length else 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            ssb.append(ss)
        }

        tvPartClick1.text = ssb
        tvPartClick1.movementMethod = LinkMovementMethod.getInstance()

        /*
        跑马灯效果
         */
        headerPaoMaDeng.setLeftClick {
            codeDialog.text(
                    """
讲解一下会碰到的属性：
android:ellipsize: 如果文本长度大于TextView的显示长度，则隐藏那一部分，可赋值为：none(不隐藏)、start（隐藏开始）、middle（隐藏中间）、end（隐藏结束）、marquee（滚动效果）。
android:marqueRepeatLimit：设定需要重复动画的次数，传递一个int值，-1为无限循环。
android:focusable：是否允许获得焦点，传递一个bool值。
android:focusableInTouchMode：是否在获得焦点时对控件有联系，传递一个bool值。
                    """.trimIndent()
            )
        }

        /*
        带阴影的TextView
         */
        headerShadow.setLeftClick {
            codeDialog.text("""
android:shadowColor:设置阴影颜色,需要与shadowRadius一起使用哦!
android:shadowRadius:设置阴影的模糊程度,设为0.1就变成字体颜色了,建议使用3.0
android:shadowDx:设置阴影在水平方向的偏移,就是水平方向阴影开始的横坐标位置
android:shadowDy:设置阴影在竖直方向的偏移,就是竖直方向阴影开始的纵坐标位置
            """.trimIndent())
        }

        headerShadow.setRightClick {
            codeDialog.text("""
                 <TextView
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:padding="10dp"
                    android:shadowColor="#F9F900"
                    android:shadowDx="10"
                    android:shadowDy="10"
                    android:shadowRadius="3"
                    android:text="带阴影的TextView"
                    android:textStyle="bold"
                    android:textSize="20sp" />
            """.trimIndent())
        }

        /*
        带图片的PictureView
         */
        headerPicture.setLeftClick {
            codeDialog.text("""
                在XML中设置
                drawableTop(上)
                drawableBottom(下)
                drawableLeft(左)
                drawableRight(右)
                drawablePadding:设置图片与文字间的间距！

                在代码中设置(注意drawable必须调用setBounds来设置边界)
                setCompoundDrawables(leftDrawable, topDrawable, rightDrawable, bottomDrawable)

            """.trimIndent())
        }

        headerPicture.setRightClick {
            codeDialog.text("""
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:padding="10dp"
                android:text="花开花落"
                android:textSize="30sp"
                android:layout_gravity="center_horizontal"
                android:drawablePadding="6dp"
                android:drawableLeft="@mipmap/ic_launcher"
                android:drawableTop="@mipmap/ic_launcher"
                android:drawableRight="@mipmap/ic_launcher"
                android:drawableBottom="@mipmap/ic_launcher"/>

            val drawable = resources.getDrawable(R.mipmap.ic_launcher).apply {
                setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            }
            tvPic.compoundDrawablePadding = 16
            tvPic.setCompoundDrawables(drawable, null, drawable, null)
            """.trimIndent())
        }



        val drawable = resources.getDrawable(R.mipmap.icon).apply {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        }
        tvPic.compoundDrawablePadding = 16
        tvPic.setCompoundDrawables(drawable, null, drawable, null)


    }

}
