package com.gmail.martsulgp.workoutpartner.presentation.extras

import java.util.Calendar
import java.util.GregorianCalendar
import kotlin.String.Companion

/**
 * Created by g_washingt0n on 20.05.2018.
 */
object CustomDateUtils {

    fun millisToTime(millis: Long): String {
        val cal = GregorianCalendar()
        cal.timeInMillis = millis
        return String.format("%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))
    }

    fun millisToDate(millis: Long): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = millis
        return String.format("%02d.%02d", cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH)) + "." + cal.get(Calendar.YEAR)
    }

    fun timeToMillis(hours: Int, minutes: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hours)
        calendar.set(Calendar.MINUTE, minutes)
        return calendar.timeInMillis
    }

    fun timeToFormattedStr(h: Int, m: Int): String {
        return String.format("%02d:%02d", h, m)
    }


}
