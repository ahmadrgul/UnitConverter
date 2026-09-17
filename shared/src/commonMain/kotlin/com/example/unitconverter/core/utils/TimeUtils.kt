package com.example.unitconverter.core.utils

import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.Instant
import kotlin.time.Clock

interface DayTime {
    val day: String
    val time: String
}
fun formatTimestamp(timestamp: Long): DayTime  {
    val timezone = kotlinx.datetime.TimeZone.currentSystemDefault()

    val currentMillis = Clock.System.now().toEpochMilliseconds()
    val currentDate = Instant.fromEpochMilliseconds(currentMillis).toLocalDateTime(timezone).date

    val targetDate = Instant.fromEpochMilliseconds(timestamp).toLocalDateTime(timezone).date
    val targetTime = Instant.fromEpochMilliseconds(timestamp).toLocalDateTime(timezone).time

    val daysDiff = currentDate.toEpochDays() - targetDate.toEpochDays()
    val daySmart = when (daysDiff) {
        0 -> "Today"
        1 -> "Yesterday"
        in 2..6 -> targetDate.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
        else -> {
            val month = targetDate.month.name.lowercase().replaceFirstChar { it.uppercase() }
            val day = targetDate.dayOfMonth
            "$month $day"
        }
    }

    val hour24 = targetTime.hour
    val hour12 = if (hour24 % 12 == 0) 12 else hour24 % 12
    val minute = targetTime.minute

    val amPm = if (hour24 >= 12) "PM" else "AM"

    val displayHour = hour12.toString().padStart(2, '0')
    val displayMinute = minute.toString().padStart(2, '0')

    return object : DayTime {
        override val day: String = daySmart
        override val time: String = "$displayHour:$displayMinute $amPm"
    }
}