package com.jober.avinashchintareddy.a10khourgoal

import android.content.res.Resources
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

private val ONE_MINUTE_MILLIS = TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES)
private val ONE_HOUR_MILLIS = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)

//fun convertDurationToFormatted(startTimeMilli: Long, endTimeMilli: Long, res: Resources): String {
//    val durationMilli = endTimeMilli - startTimeMilli
//    val weekdayString = SimpleDateFormat("EEEE", Locale.getDefault()).format(startTimeMilli)
//    return when {
//        durationMilli < ONE_MINUTE_MILLIS -> {
//            val seconds = TimeUnit.SECONDS.convert(durationMilli, TimeUnit.MILLISECONDS)
//            res.getString(R.string.seconds_length, seconds, weekdayString)
//        }
//        durationMilli < ONE_HOUR_MILLIS -> {
//            val minutes = TimeUnit.MINUTES.convert(durationMilli, TimeUnit.MILLISECONDS)
//            res.getString(R.string.minutes_length, minutes, weekdayString)
//        }
//        else -> {
//            val hours = TimeUnit.HOURS.convert(durationMilli, TimeUnit.MILLISECONDS)
//            res.getString(R.string.hours_length, hours, weekdayString)
//        }
//    }
//}

class Util(time:Long){
    val mDate: String
    val mHour: String
    val mMinute: String
    val mSecond: String
    val mtime: String
    init {
        mDate = SimpleDateFormat("MM/dd/YY", Locale.getDefault()).format(time)
        mtime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(time)

        mHour = mtime.split(":")[0]
        mMinute=mtime.split(":")[1]
        mSecond=mtime.split(":")[2]
    }
    //
}

