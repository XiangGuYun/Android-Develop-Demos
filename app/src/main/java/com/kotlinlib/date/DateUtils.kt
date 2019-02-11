package com.kotlinlib.date

import java.text.SimpleDateFormat
import java.util.*

interface DateUtils {

    val days31:ArrayList<Int> get() = arrayListOf(1, 3, 5, 7, 8, 10, 12)

    fun Date.toFmt(fmt:String): String {
        return SimpleDateFormat(fmt).format(this).toString()
    }

    /**
     * 返回文字描述的日期
     */
    fun getTimeFormatText(date: Date?, dateFormat: String): String? {

        val minute = (60 * 1000).toLong()// 1分钟
        val hour = 60 * minute// 1小时
        val day = 24 * hour// 1天
        val month = 31 * day// 月
        val year = 12 * month// 年

        if (date == null) {
            return null
        }
        val diff = Date().time - date.time
        var r: Long = 0
        if (diff > year) {
            val sdf = SimpleDateFormat(dateFormat)
            return sdf.format(date)
        }
        if (diff > month) {
            r = diff / month
            return r.toString() + "个月前"
        }
        if (diff > day) {
            r = diff / day
            return r.toString() + "天前"
        }
        if (diff > hour) {
            r = diff / hour
            return r.toString() + "个小时前"
        }
        if (diff > minute) {
            r = diff / minute
            return r.toString() + "分钟前"
        }
        return "刚刚"
    }


    /**
     * 获取日期的星期索引
     * @param date
     * @return
     */
    fun getWeekIndex(date: Date): Int {
        val weekDays = intArrayOf(0, 1, 2, 3, 4, 5, 6)
        val cal = Calendar.getInstance()
        cal.time = date
        var w = cal.get(Calendar.DAY_OF_WEEK) - 1
        if (w < 0)
            w = 0
        return weekDays[w]
    }


    /**
     * EEEE：星期
     * EEE：周
     * @param date
     * @return
     */
    fun getWeekDay(date: Date): String {
        val dateFm = SimpleDateFormat("EEE")
        return dateFm.format(date)
    }

    /**
     * 判断是否是闰年
     * @param year
     * @return
     */
    fun isRunYear(year: Int): Boolean {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0
    }

    /**
     * 获取一个月有多少天
     * @param month
     * @param year
     * @return
     */
    fun getMonthDays(month: Int, year: Int): Int {
        return if (month == 2) {
            if (isRunYear(year)) 29 else 28
        } else if (days31.contains(month)) {
            31
        } else {
            30
        }
    }

    /**
     * 获取当前月份是第几季度
     * @param currMonth Int
     * @return Int
     */
    fun getSeason(currMonth: Int): Int {
        if (currMonth == 1 || currMonth == 2 || currMonth == 3)
            return 1
        if (currMonth == 4 || currMonth == 5 || currMonth == 6)
            return 2
        if (currMonth == 7 || currMonth == 8 || currMonth == 9)
            return 3
        return if (currMonth == 10 || currMonth == 11 || currMonth == 12) 4 else 1
    }

    /**
     * 当月第一天
     * @return
     */
    fun getFirstDay(): String {
        val df = SimpleDateFormat("yyyy/MM/dd")
        val calendar = Calendar.getInstance()
        val theDate = calendar.time
        val gcLast = Calendar.getInstance() as GregorianCalendar
        gcLast.time = theDate
        gcLast.set(Calendar.DAY_OF_MONTH, 1)
        val day_first = df.format(gcLast.time)
        val str = StringBuffer().append(day_first)
        return str.toString()

    }

    /**
     * 当月最后一天
     * @return
     */
    fun getLastDay(): String {
        val df = SimpleDateFormat("yyyy/MM/dd")
        val calendar = Calendar.getInstance()
        val theDate = calendar.time
        val s = df.format(theDate)
        calendar.add(Calendar.MONTH, 1)//加一个月
        calendar.set(Calendar.DATE, 1)//设置为该月第一天
        calendar.add(Calendar.DATE, -1) //再减一天即为上个月最后一天
        var day_last = df.format(calendar.time)
        val endStr = StringBuffer().append(day_last)
        day_last = endStr.toString()
        val str = StringBuffer().append(day_last)
        return str.toString()

    }
}