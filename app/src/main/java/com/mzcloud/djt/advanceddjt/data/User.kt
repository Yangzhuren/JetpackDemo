package com.mzcloud.djt.advanceddjt.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
        tableName = "user"
)
data class User(
        @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val userId: Long,
        @ColumnInfo(name = "account") val account: String = "",
        @ColumnInfo(name = "password") val password: String = ""
) {
    @ColumnInfo(name = "user_type")
    var userType: Int = 0
        set(value) {
            userType = value
        }

    @ColumnInfo(name = "real_name")
    var realName: String = ""
        set(value) {
            realName = value
        }

    @ColumnInfo(name = "mobile")
    var mobile: String = ""
        set(value) {
            mobile = value
        }

    @ColumnInfo(name = "session_id")
    var sessionId: String = ""
        set(value) {
            sessionId = value
        }

    @ColumnInfo(name = "device_id")
    var deviceId: String = ""
        set(value) {
            deviceId = value
        }

    @ColumnInfo(name = "dic_ver")
    var dicVer: Int = 0
        set(value) {
            dicVer = value
        }

    @ColumnInfo(name = "date")
    var date: Calendar = Calendar.getInstance()
}