package com.mzcloud.djt.advanceddjt.data

import androidx.room.*

@Entity(
        tableName = "app_role",
        foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["user_id"])],
        indices = [Index("user_id")],
        primaryKeys = ["user_id", "role_code"]
)
data class AppRole(
        @ColumnInfo(name = "user_id") var userId: Long,
        @ColumnInfo(name = "role_code") val roleCode: Int,
        @ColumnInfo(name = "role_name") val roleName: String
) {
}