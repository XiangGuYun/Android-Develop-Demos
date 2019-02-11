package com.kotlinlib.transfer

import android.os.Message

interface MessageUtils {

    val msg get() = Message.obtain()

    fun Message.setWhat(what:Int): Message {
        this.what=what
        return this
    }

    infix fun Message.w(what:Int): Message {
        this.what=what
        return this
    }

    fun Message.setObj(any:Any): Message {
        this.obj=any
        return this
    }

    fun Message.o(any:Any): Message {
        this.obj=any
        return this
    }

    fun Message.setArg1(int:Int): Message {
        this.arg1=int
        return this
    }
    fun Message.setArg2(int:Int): Message {
        this.arg2=int
        return this
    }
}