package fr.aylabs.dates

import kotlinx.datetime.TimeZone
import kotlinx.datetime.YearMonth
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

fun formatYearMonth(yearMonth: YearMonth): String {
    val monthNames = listOf(
        "Jan", "Fév", "Mar", "Avr", "Mai", "Jun",
        "Jul", "Aoû", "Sep", "Oct", "Nov", "Déc"
    )
    return "${monthNames[yearMonth.month.number - 1]} ${yearMonth.year}"
}

fun monthsBetween(start: YearMonth, end: YearMonth?): Int {
    // Return number of months between two YearMonth
    // if end is null, return number of months between start and now
    val now = Clock.System.now()
    val newEnd = end ?: YearMonth(
        year = now.toLocalDateTime(TimeZone.UTC).year,
        month = now.toLocalDateTime(TimeZone.UTC).month.number
    )

    val a = start.rangeTo(newEnd)
    return a.size
}