package com.mzcloud.djt.advanceddjt.dao

import androidx.room.*
import com.mzcloud.djt.advanceddjt.data.AppRole

@Dao
interface DAppRoleDao {
    @Query("select *from app_role where user_id = :userId")
    fun getUserRolesByUserId(userId: Long): List<AppRole>

    @Delete()
    fun deleteUserRoles(appRoles: List<AppRole>): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoles(appRoles: List<AppRole>)
}