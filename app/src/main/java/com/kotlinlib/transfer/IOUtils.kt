package com.kotlinlib.transfer

import android.os.NetworkOnMainThreadException
import android.util.Log
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.net.URL
import java.nio.charset.Charset

interface IOUtils {

    val bus get() = EventBus.getDefault()

    /**
     * 获取文本文件的内容
     * @param fileName 文件名
     * @param charset 字符集
     */
    fun fileText(fileName:String,charset: Charset = Charsets.UTF_8):String{
        return File(fileName).readText(charset)
    }

    /**
     * 获取文件每一行内容，存入一个 List 中
     * @param filename
     */
    fun fileLines(filename: String,charset: Charset = Charsets.UTF_8): List<String> {
        return File(filename).readLines(charset)
    }


    fun fileBytes(filename: String):ByteArray{
        return File(filename).readBytes()
    }

    /**
     * 覆盖写文件
     */
    fun writeFile(text: String, destFile: String) {
        val f = File(destFile)
        if (!f.exists()) {
            f.createNewFile()
        }
        f.writeText(text, Charset.defaultCharset())
    }

    /**
     * 末尾追加写文件
     */
    fun appendFile(text: String, destFile: String) {
        val f = File(destFile)
        if (!f.exists()) {
            f.createNewFile()
        }
        f.appendText(text, Charset.defaultCharset())
    }

    /**
     * 文件树
     */
    fun getFileIterator(filename: String): Iterator<File> {
        val f = File(filename)
        val fileTreeWalk = f.walk()
        return fileTreeWalk.iterator()
    }

    /**
     * 根据 url 获取该 url 的响应 HTML函数
     */
    fun getUrlContent(url: String): String {
        return try {
            URL(url).readText(Charset.defaultCharset())
        }catch (e:NetworkOnMainThreadException){
            Log.e("exception","错误：在主线程中执行网络操作-${e.message}")
            ""
        }
    }

    /**
     * 根据 url 获取该 url 响应比特数组函数
     */
    fun getUrlBytes(url: String): ByteArray {
        return URL(url).readBytes()
    }

    /**
     * 把 url 响应字节数组写入文件
     */
    fun writeUrlBytesTo(filename: String, url: String) {
        File(filename).writeBytes(getUrlBytes(url))
    }




}