package com.kotlinlib.persistence

import android.content.Context
import android.support.v4.content.SharedPreferencesCompat


interface SPUtils {

//    val FILE_NAME: String
//        get() = "sputils"
    companion object {
        const val FILE_NAME = "sputils"

    fun getSP(ctx:Context, key:String, defaultObject:Any):Any
    {
        var sp = ctx.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

        if (defaultObject is String)
        {
            return sp.getString(key, defaultObject.toString())
        } else if (defaultObject is Int)
        {
            return sp.getInt(key, defaultObject.toInt())
        } else if (defaultObject is Boolean)
        {
            return sp.getBoolean(key, defaultObject as Boolean)
        } else if (defaultObject is Float)
        {
            return sp.getFloat(key, defaultObject.toFloat())
        } else if (defaultObject is Long)
        {
            return sp.getLong(key, defaultObject.toLong())
        }

        return Any()
    }

}

    fun Context.putSP(key: String, obj: Any) {

        val sp = getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE)
        val editor = sp.edit()

        if (obj is String) {
            editor.putString(key, obj)
        } else if (obj is Int) {
            editor.putInt(key, obj)
        } else if (obj is Boolean) {
            editor.putBoolean(key, obj)
        } else if (obj is Float) {
            editor.putFloat(key, obj)
        } else if (obj is Long) {
            editor.putLong(key, obj)
        } else {
            editor.putString(key, obj.toString())
        }

        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor)
    }

     fun Context.getSP(key:String, defaultObject:Any):Any
        {
            var sp = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

            if (defaultObject is String)
            {
                return sp.getString(key, defaultObject.toString())
            } else if (defaultObject is Int)
            {
                return sp.getInt(key, defaultObject.toInt())
            } else if (defaultObject is Boolean)
            {
                return sp.getBoolean(key, defaultObject as Boolean)
            } else if (defaultObject is Float)
            {
                return sp.getFloat(key, defaultObject.toFloat())
            } else if (defaultObject is Long)
            {
                return sp.getLong(key, defaultObject.toLong())
            }

            return Any()
        }

    fun Context.remove(key: String) {
        val sp = getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.remove(key)
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor)
    }

    fun Context.clearSP() {
        val sp = getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.clear()
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor)
    }

}