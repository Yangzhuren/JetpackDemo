package com.mzcloud.djt.advanceddjt.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "config")
data class Configuration(
        @ColumnInfo(name = "open_version") val openVersion: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var openId: Long = 0

    @ColumnInfo(name = "open_date")
    var openDate: Calendar = Calendar.getInstance()
}