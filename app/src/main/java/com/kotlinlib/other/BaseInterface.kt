package com.kotlinlib.other

import android.os.Message
import android.widget.TextView
import com.kotlinlib.activity.ContextUtils
import com.kotlinlib.bitmap.BmpUtils
import com.kotlinlib.date.DateUtils
import com.kotlinlib.listener.OnPageChange
import com.kotlinlib.listener.OnTabSelected
import com.kotlinlib.net.NetUtils
import com.kotlinlib.persistence.SPUtils
import com.kotlinlib.transfer.IOUtils
import com.kotlinlib.transfer.MessageUtils
import com.kotlinlib.view.base.ViewUtils
import com.kotlinlib.view.recyclerview.RVInterface
import com.kotlinlib.view.textview.TextViewUtils

interface BaseInterface: SPUtils, TextViewUtils, StringUtils,
        DensityUtils, ViewUtils, BmpUtils, NetUtils,DateUtils,
        RVInterface, IOUtils, ContextUtils, MessageUtils, OnPageChange, OnTabSelected{

    fun <T: TextView> T.text_(text:String?): T {
        if(text.isNullOrEmpty()){
            this.text = "-"
        }else{
            this.text = text
        }
        return this
    }

    fun <T: TextView> T.text_phone(text:String?): T {
        if(text.isNullOrEmpty()){
            this.text = "-"
        }else{
            this.text = text?.encryptPhone()
        }
        return this
    }

    fun busPost(callback:()->Message){
        bus.post(callback.invoke())
    }

}