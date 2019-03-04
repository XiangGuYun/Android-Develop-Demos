package com.androidui.kotlin

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_reflect.*
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.*
import kotlin.reflect.jvm.isAccessible

@LayoutId(R.layout.activity_reflect)
class ReflectActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {

        val clazz = Canvas::class
        val clazz1 = Paint::class
        getInfo(clazz)
        createObject(clazz1)
        constructorQuote()
        invokeMethod(clazz1)
        AccessProperty()
    }

    class Person(var name:String)

    private fun AccessProperty() {
        val clz = Person::class
        val person = clz.constructors.toMutableList()[0].call("")
        val properties = clz.declaredMemberProperties.toMutableList()
        val prop = properties.find {
            it.name == "name"
        } as KMutableProperty1<Person, String>
        prop.set(person, "小明")
        println("此人的姓名是：${prop.get(person)}")
        tv15.text="""
val clz = Person::class
val person = clz.constructors.toMutableList()[0].call("")
val properties = clz.declaredMemberProperties.toMutableList()
val prop = properties.find {
    it.name == "name"
} as KMutableProperty1<Person, String>
prop.set(person, "小明")
println("此人的姓名是：$'{prop.get(person)}")//小明
        """.trimIndent()
    }

    private fun invokeMethod(clazz: KClass<Paint>) {
        val paint = clazz.createInstance()
        val functions = clazz.declaredFunctions.toMutableList()
        //获取对应方法名的索引值
        val antiAliasIndex = functions.indexOfFirst {
            it.name == "setAntiAlias"
        }
        //直接获取对应方法
        val setDither = functions.find {
            it.name == "setDither"
        }
        functions[antiAliasIndex].call(paint, true)
        setDither?.call(paint, true)
        println("通过反射设置的抗锯齿结果是：${paint.isAntiAlias}")
        println("通过反射设置的抖动结果是：${paint.isDither}")

        tv14.text = """
val paint = clazz.createInstance()
val functions = clazz.declaredFunctions.toMutableList()
//获取对应方法名的索引值
val antiAliasIndex = functions.indexOfFirst {
    it.name == "setAntiAlias"
}
//直接获取对应方法
val setDither = functions.find {
    it.name == "setDither"
}
functions[antiAliasIndex].call(paint, true)
setDither?.call(paint, true)
println("通过反射设置的抗锯齿结果是：$'{paint.isAntiAlias}")//true
println("通过反射设置的抖动结果是：$'{paint.isDither}")//true
        """.trimIndent()
    }

    private fun constructorQuote() {
        test(::Paint)
        tv12B.text = """
        fun constructorQuote() {
            test(::Paint)
        }

        fun test(func:(Int)->Paint){
            val paint = func(ANTI_ALIAS_FLAG)
            println(paint.isAntiAlias)
        }
        """.trimIndent()
    }

    fun test(func:(Int)->Paint){
        val paint = func(ANTI_ALIAS_FLAG)
        println(paint.isAntiAlias)
    }

    private fun createObject(clazz: KClass<Paint>) {
        //调用无参构造方法
        val paint = clazz.createInstance()
        println("抗锯齿：${paint.isAntiAlias}")
        //调用有参构造方法
        val con = clazz.constructors.toMutableList()[1]
        val paint1 = con.call(ANTI_ALIAS_FLAG)
        println("抗锯齿：${paint1.isAntiAlias}")

        tv12.text = """
//调用无参构造方法
val paint = clazz.createInstance()
println("抗锯齿：$'{paint.isAntiAlias}")

//调用有参构造方法
val con = clazz.constructors.toMutableList()[1]
val paint1 = con.call(ANTI_ALIAS_FLAG)
println("抗锯齿：$'{paint1.isAntiAlias}")
（打印）
        """.trimIndent()
        tv12.setSimpleClickText {
            codeDialog.text("""
抗锯齿：${paint.isAntiAlias}
抗锯齿：${paint1.isAntiAlias}
""".trimIndent())
        }
    }

    private fun getInfo(clazz: KClass<Canvas>) {
        tv1.text = "constructors：获取全部构造器（结果）"
        val build1 = StringBuilder()
        clazz.constructors.forEach {build1.append(it.toString()+"\n\n")}
        tv1.setSimpleClickText {
            codeDialog.text(build1.toString())
        }

        tv2.text = "primaryConstructor：获取主构造器（结果）"
        tv2.setSimpleClickText {
            codeDialog.text(clazz.primaryConstructor.toString())
        }

        tv3.text = "functions：获取类的所有方法（结果）"
        val build2 = StringBuilder()
        clazz.functions.forEach {build2.append(it.toString()+"\n\n")}
        tv3.setSimpleClickText {
            codeDialog.text(build2.toString())
        }

        tv4.text = "declaredFunctions：获取子类特有的所有方法（结果）"
        val build3 = StringBuilder()
        clazz.declaredFunctions.forEach {build3.append(it.toString()+"\n\n")}
        tv4.setSimpleClickText {
            codeDialog.text(build3.toString())
        }

        tv5.text = "declaredMemberFunctions：获取子类特有的所有成员方法（结果）"
        val build4 = StringBuilder()
        clazz.declaredMemberFunctions.forEach {build4.append(it.toString()+"\n\n")}
        tv5.setSimpleClickText {
            codeDialog.text(build4.toString())
        }

        tv6.text = "declaredMemberExtensionProperties：获取子类特有的所有成员扩展方法（结果）"
        val build5 = StringBuilder()
        clazz.declaredMemberExtensionProperties.forEach {build5.append(it.toString()+"\n\n")}
        tv6.setSimpleClickText {
            codeDialog.text(build5.toString())
        }

        tv7.text = "declaredMemberProperties：获取子类特有的全部成员属性（结果）"
        val build6 = StringBuilder()
        clazz.declaredMemberProperties.forEach {build6.append(it.toString()+"\n\n")}
        tv7.setSimpleClickText {
            codeDialog.text(build6.toString())
        }

        tv8.text = "memberProperties：获取全部成员属性（结果）"
        val build7 = StringBuilder()
        clazz.memberProperties.forEach {build7.append(it.toString()+"\n\n")}
        tv8.setSimpleClickText {
            codeDialog.text(build7.toString())
        }

        tv9.text = "nestedClasses：获取全部嵌套类（结果）"
        val build8 = StringBuilder()
        clazz.nestedClasses.forEach {build8.append(it.toString()+"\n\n")}
        tv9.setSimpleClickText {
            codeDialog.text(build8.toString())
        }

        tv10.text = "supertypes：获取全部父类型（结果）"
        val build9 = StringBuilder()
        clazz.supertypes.forEach {build9.append(it.toString()+"\n\n")}
        tv10.setSimpleClickText {
            codeDialog.text(build9.toString())
        }

        tv11.text = "annotations：获取全部注解（结果）"
        val build10 = StringBuilder()
        clazz.annotations.forEach {build10.append(it.toString()+"\n\n")}
        tv11.setSimpleClickText {
            codeDialog.text(build10.toString())
        }
    }
}
