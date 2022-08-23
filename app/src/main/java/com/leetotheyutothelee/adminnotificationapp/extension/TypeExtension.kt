package com.leetotheyutothelee.adminnotificationapp.extension

import android.annotation.SuppressLint
import com.leetotheyutothelee.adminnotificationapp.constant.ViewConstant.SERVER_DATE_FORMAT
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.toDate(format: String): String? {
    return try {
        val fmt = SimpleDateFormat(SERVER_DATE_FORMAT).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        val fmt2 = SimpleDateFormat(format).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        val value = fmt.parse(this)
        fmt2.format(value!!)
    } catch (pe: ParseException) {
        null
    }
}