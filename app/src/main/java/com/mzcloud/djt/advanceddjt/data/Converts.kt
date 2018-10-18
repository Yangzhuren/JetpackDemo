package com.mzcloud.djt.advanceddjt.data

import androidx.room.TypeConverter
import java.util.*

class Converts {
    @TypeConverter
    fun calendarToTimestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun timestampToCalendar(timestamp: Long): Calendar = Calendar.getInstance().apply { timeInMillis = timestamp }
}