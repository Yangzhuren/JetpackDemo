package com.mzcloud.djt.advanceddjt.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mzcloud.djt.advanceddjt.data.User

@Dao
interface DUserDao {
    @Query("SELECT *FROM user")
    fun getLoginUsers(): LiveData<List<User>>

    @Query("SELECT *from user ORDER BY date DESC LIMIT(1)")
    fun getLastLoginUser(): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long
}