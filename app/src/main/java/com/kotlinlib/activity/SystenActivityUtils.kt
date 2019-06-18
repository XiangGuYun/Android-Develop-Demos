package com.kotlinlib.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri

interface SystenActivityUtils {

    /**
     * 打电话
     * @receiver Activity
     * @param phoneNumber String
     */
    fun Activity.callPhone(phoneNumber:String){
        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber")))
    }

    /**
     * 发短信
     * @receiver Activity
     * @param phoneNumber String
     * @param message String
     */
    fun Activity.sendSMS(phoneNumber:String, message:String){
        val uri = Uri.parse("smsto:$phoneNumber")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", message)
        startActivity(intent)
    }

    /**
     * 打开浏览器查看网页
     * @receiver Activity
     * @param url String
     */
    fun Activity.openBrowser(url:String){
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

}