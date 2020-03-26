package com.catsoft.charts.services

import java.text.SimpleDateFormat
import java.util.*

class CalendarReadableUtil(private val currentLocaleProvider: CurrentLocaleProvider) {
    fun format(calendar: Calendar): String {

        val locale = currentLocaleProvider.currentLocale

        val yesterdayDate = Calendar.getInstance()
        yesterdayDate.add(Calendar.DAY_OF_MONTH, -1)

        val now = Calendar.getInstance()

        return when (calendar) {
            now -> "Сегодня"
            yesterdayDate -> "Вчера"
            else -> {
                return (if (calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
                    val dateFormat = SimpleDateFormat("d MMMM", locale)
                    dateFormat.format(calendar.time)
                } else {
                    val dateFormat = SimpleDateFormat("d MMMM YYYY", locale)
                    dateFormat.format(calendar.time)
                }).replace(".", "")
            }
        }
    }
}