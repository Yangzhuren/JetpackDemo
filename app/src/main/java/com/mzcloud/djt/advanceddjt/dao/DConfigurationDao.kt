package com.mzcloud.djt.advanceddjt.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mzcloud.djt.advanceddjt.data.Configuration

@Dao
interface DConfigurationDao {

    @Query("select *from config where open_version = :version")
    fun getCurrentVersionOpenedRecords(version: String?): LiveData<List<Configuration>>

    @Insert
    fun insertCurrentOpenRecord(configuration: Configuration): Long
}