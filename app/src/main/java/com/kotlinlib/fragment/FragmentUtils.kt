package com.kotlinlib.fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import java.util.*

/**
 * Created by asus on 2016/7/20.
 * Activity托管Fragment的便捷工具类
 */
class FragmentUtils<T:Fragment> {

    private var manager: FragmentManager? = null
    var fragments = ArrayList<T>()
    private var contentId: Int = 0
    private var act:FragmentActivity

    constructor(a: FragmentActivity, fragment: T, contentId: Int) {
        act = a
        manager = a.supportFragmentManager
        this.contentId = contentId
        val transaction = manager!!.beginTransaction()
        fragments.add(fragment)
        transaction.replace(contentId, fragment)
        transaction.commit()
    }

    constructor(a: FragmentActivity, list: ArrayList<T>, contentId: Int) {
        act = a
        manager = a.supportFragmentManager
        this.contentId = contentId
        val transaction = manager!!.beginTransaction()
        fragments.addAll(list)
        transaction.replace(contentId, list[0])
        transaction.commit()
    }

    fun remove(fragment: T) {
        val transaction = manager!!.beginTransaction()
        fragments.remove(fragment)
        transaction.remove(fragment)
        transaction.commit()
    }

    /**
     * 加载、显示、隐藏Fragment的便捷方法
     * @param targetFragment 需要显示的Fragment
     */
    fun switch(targetFragment: T): Boolean {
        fragments.remove(targetFragment)
        val transaction = manager!!.beginTransaction()
        if (!targetFragment.isAdded) {    // 先判断是否被add过
            for (i in fragments.indices) {
                if (fragments[i].isAdded) transaction.hide(fragments[i])
            }
            transaction.add(contentId, targetFragment).commit() // 隐藏当前的fragment，add下一个到Activity中
        } else {
            for (i in fragments.indices) {
                if (fragments[i].isAdded) transaction.hide(fragments[i])
            }
            transaction.show(targetFragment).commit() // 隐藏当前的fragment，显示下一个
        }
        fragments.add(targetFragment)
        return true
    }

    fun switch(targetFragment: T, getTransaction:(FragmentTransaction)->Unit): Boolean {
        //fragments.remove(targetFragment)
        val transaction = manager!!.beginTransaction()
        getTransaction.invoke(transaction)
        if (!targetFragment.isAdded) {    // 先判断是否被add过
            for (i in fragments.indices) {
                if (fragments[i].isAdded) transaction.hide(fragments[i])
            }
            transaction.add(contentId, targetFragment).commit() // 隐藏当前的fragment，add下一个到Activity中
        } else {
            for (i in fragments.indices) {
                if (fragments[i].isAdded) transaction.hide(fragments[i])
            }
            transaction.show(targetFragment).commit() // 隐藏当前的fragment，显示下一个
        }
        //fragments.add(targetFragment)
        return true
    }

    fun switch(index: Int): Boolean {
        val targetFragment = fragments[index]
        //fragments.remove(targetFragment)
        val transaction = manager!!.beginTransaction()
        if (!targetFragment.isAdded) {    // 先判断是否被add过
            for (i in fragments.indices) {
                if (fragments[i].isAdded) transaction.hide(fragments[i])
            }
            transaction.add(contentId, targetFragment).commit() // 隐藏当前的fragment，add下一个到Activity中
        } else {
            for (i in fragments.indices) {
                if (fragments[i].isAdded) transaction.hide(fragments[i])
            }
            transaction.show(targetFragment).commit() // 隐藏当前的fragment，显示下一个
        }
        //fragments.add(targetFragment)
        return true
    }

    fun switch(index: Int, getTransaction:(FragmentTransaction)->Unit): Boolean {
        val targetFragment = fragments[index]
        //fragments.remove(targetFragment)
        val transaction = manager!!.beginTransaction()
        getTransaction.invoke(transaction)
        if (!targetFragment.isAdded) {    // 先判断是否被add过
            for (i in fragments.indices) {
                if (fragments[i].isAdded) transaction.hide(fragments[i])
            }
            transaction.add(contentId, targetFragment).commit() // 隐藏当前的fragment，add下一个到Activity中
        } else {
            for (i in fragments.indices) {
                if (fragments[i].isAdded) transaction.hide(fragments[i])
            }
            transaction.show(targetFragment).commit() // 隐藏当前的fragment，显示下一个
        }
        //fragments.add(targetFragment)
        return true
    }

    /**
     * 切换到对应的Fragment并将其加入到回退栈中
     */
    fun switchFragmentWithStack(targetFragment: T) {
        fragments.remove(targetFragment)
        val transaction = manager!!.beginTransaction()
        if (!targetFragment.isAdded) {    // 先判断是否被add过
            for (i in fragments.indices) {
                if (fragments[i].isAdded) transaction.hide(fragments[i])
            }
            transaction.add(contentId, targetFragment)// 隐藏当前的fragment，add下一个到Activity中
                    .addToBackStack(null)
                    .commit()

        } else {
            for (i in fragments.indices) {
                if (fragments[i].isAdded) transaction.hide(fragments[i])
            }
            transaction.show(targetFragment).commit() // 隐藏当前的fragment，显示下一个
        }
        fragments.add(targetFragment)
    }

    /**
     * 切换到一个新的Fragment
     * @param target Fragment
     * @param isAddToBackStack Boolean
     */
    fun replace(target:Fragment, isAddToBackStack: Boolean, getTransaction:(FragmentTransaction)->Unit ){
        val manager = act.supportFragmentManager.beginTransaction()
        getTransaction.invoke(manager)
        if(isAddToBackStack){
            manager.replace(contentId, target,target.javaClass.simpleName)
            manager.addToBackStack(target.javaClass.simpleName)
        }else{
            manager.replace(contentId, target)
        }
        manager.commit()
    }

}
