package com.gmail.martsulgp.workoutpartner.presentation.extras

/**
 * Created by g_washingt0n on 02.05.2018.
 */

enum class WeekdaysEnum {
    Monday(1),
    Tuesday(2),
    Wednesday(3),
    Thursday(4),
    Friday(5),
    Saturday(6),
    Sunday(7),
    Mon("Monday"),
    Tue("Tuesday"),
    Wed("Wednesday"),
    Thu("Thursday"),
    Fri("Friday"),
    Sat("Saturday"),
    Sun("Sunday");

    var dayAsInt: Int = 0
    lateinit var dayAsString: String

    private constructor(number: Int) {
        this.dayAsInt = number
    }

    private constructor(day: String) {
        this.dayAsString = day
    }

    companion object {


        fun convertIntToDay(num: Int): String? {
            for (day in WeekdaysEnum.values()) {
                if (day.dayAsInt == num) {
                    return day.toString()
                }
            }
            return null
        }

        fun convertDayToInt(inputDay: String): Int {
            for (day in WeekdaysEnum.values()) {
                if (day.toString() == inputDay) {
                    return day.dayAsInt
                }
            }
            return -1
        }

        fun convertIntToShortDay(num: Int): String? {
            val fullDay = convertIntToDay(num)!!
            when (fullDay) {
                "Monday" -> return "Mon"
                "Tuesday" -> return "Tue"
                "Wednesday" -> return "Wed"
                "Thursday" -> return "Thu"
                "Friday" -> return "Fri"
                "Saturday" -> return "Sat"
                "Sunday" -> return "Sun"
                else -> return null
            }
            //        TODO replace switch with correct enum functionality
            //        for (WeekdaysEnum day : WeekdaysEnum.values()) {
            //            if(day.getDayAsString().equals(fullDay)){
            //                return day.toString();
            //            }
            //        }
        }
    }
}
