/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Shengjie Sim Sun
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.kotlinlib.activity


import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.zhy.http.okhttp.OkHttpUtils
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

//import android.support.multidex.MultiDex;

class KotlinApplication : Application() {

    public override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base)
    }

    //--------------------------
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: KotlinApplication
        private val TAG = "Tinker.SampleApp"
    }

    private lateinit var _context: Context


    /**
     * 由于在onCreate替换真正的Application,
     * 我们建议在onCreate初始化TinkerPatch,而不是attachBaseContext
     */
    override fun onCreate() {
        super.onCreate()
        //----------------
        KotlinApplication.instance = this
        _context = applicationContext
        initOKHttp()
        //----------------
    }

    @Synchronized
    fun context(): KotlinApplication {
        return _context as KotlinApplication
    }


    private fun initOKHttp() {
        val okHttpClient = OkHttpClient.Builder()
                //                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build()
        OkHttpUtils.initClient(okHttpClient)
    }

}
