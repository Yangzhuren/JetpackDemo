package com.mzcloud.djt.advanceddjt.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.mzcloud.djt.advanceddjt.data.AppRole

@Dao
interface AppRoleDao {
    @Query("select *from app_role where user_id = :userId")
    fun getUserRolesByUserId(userId: Long): List<AppRole>

    @Delete()
    fun deleteUserRoles(appRoles: List<AppRole>): Int
}