package com.tanucode.levelup.util

object RRulerBuilder {
    fun yearly(month: Int, day: Int, interval: Int = 1): String{
        return "FREQ=YEARLY;" +
                "BYMONTH=$month;" +
                "BYMONTHDAY=$day;" +
                (if (interval>1) "INTERVAL=$interval;" else "")
    }
    fun monthly(day: Int, interval: Int = 1): String =
        "FREQ=MONTHLY;BYMONTHDAY=$day;" +
                (if (interval>1) "INTERVAL=$interval;" else "")

    fun weekly(daysOfWeek: List<String>, interval: Int = 1): String {
        val days = daysOfWeek.joinToString(",")
        return "FREQ=WEEKLY;BYDAY=$days;" +
                (if (interval>1) "INTERVAL=$interval;" else "")
    }

    fun daily(interval: Int = 1): String =
        "FREQ=DAILY;" + (if (interval>1) "INTERVAL=$interval;" else "")

}