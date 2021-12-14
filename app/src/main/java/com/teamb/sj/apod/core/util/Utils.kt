package com.teamb.sj.apod.core.util

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object Utils {

    /***
     * validate the format received is matching the server requirements
     */
    fun isValidDateFormat(dateStr: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        try {
            LocalDate.parse(dateStr, formatter)
        } catch (e: DateTimeParseException) {
            return false
        }
        return true
    }

    /***
     * method to return the local date from String
     */
    fun getLocalDateFromString(dateStr: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        if (isValidDateFormat(dateStr)) {
            return LocalDate.parse(dateStr, formatter)
        }
        return LocalDate.now(ZoneId.of("PST"))
    }

    /***
     * the api is supported only from 1995 -06-16 ,
     * so any date older than that will considered as an invalid date
     */
    fun isOlderDate(localDate: LocalDate): Boolean {
        val oldestDate = LocalDate.of(1995, 6, 16)
        return localDate.isBefore(oldestDate)
    }

    /***
     * the api is not supported for future dates  ,
     * so any date after than now will considered as an invalid date
     */
    fun isFutureDate(localDate: LocalDate): Boolean {
        return localDate.isAfter(LocalDate.now(ZoneId.of("PST")))
    }

}