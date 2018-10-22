package com.mzcloud.djt.advanceddjt.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
        tableName = "user"
)
data class User(
        @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val userId: Long,
        @ColumnInfo(name = "account") val account: String = ""
) {
    @ColumnInfo(name = "password")
    var password: String = ""

    @ColumnInfo(name = "user_type")
    var userType: Int = 0

    @ColumnInfo(name = "real_name")
    var realName: String = ""

    @ColumnInfo(name = "mobile")
    var mobile: String = ""

    @ColumnInfo(name = "session_id")
    var sessionId: String = ""

    @ColumnInfo(name = "device_id")
    var deviceId: String = ""

    @ColumnInfo(name = "dic_ver")
    var dicVer: Int = 0

    @ColumnInfo(name = "date")
    var date: Calendar = Calendar.getInstance()
}