package com.teamb.sj.apod.core.util

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Utils {

    /***
     * validate the format received is matching the server requirements
     */
    fun isValidDate(dateStr: Long): Boolean {
        val date = LocalDate.ofEpochDay(dateStr)
        if (isFutureDate(date) || isOlderDate(date)) {
            return false
        }
        return true
    }

    /***
     * method to return the local date from String
     */
    fun getLocalDateFromString(date: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(date, formatter)
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

    fun getHumanReadableDate(dateStr: Long): String {
        val date = LocalDate.ofEpochDay(dateStr)
        return date.toString()
    }

    fun getStartDateOfMonth(dateStr: Long): String {
        val date = LocalDate.ofEpochDay(dateStr)
        return date.withDayOfMonth(1).toString()
    }

    fun getEndDateOfMonth(dateStr: Long): String {
        val date = LocalDate.ofEpochDay(dateStr)
        val newDate = date.withDayOfMonth(date.lengthOfMonth())
        return if (isFutureDate(newDate)) {
            LocalDate.now(ZoneId.of("PST")).toString()
        } else {
            newDate.toString()
        }
    }
}
