package com.mzcloud.djt.advanceddjt.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.mzcloud.djt.advanceddjt.dao.DAppRoleDao
import com.mzcloud.djt.advanceddjt.dao.DConfigurationDao
import com.mzcloud.djt.advanceddjt.dao.DUserDao
import com.mzcloud.djt.advanceddjt.utils.DATABASE_NAME
import com.mzcloud.djt.advanceddjt.workers.InitialWorker

@Database(entities = [Configuration::class, AppRole::class, User::class], version = 1, exportSchema = true)
@TypeConverters(Converts::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun configurationDao(): DConfigurationDao
    abstract fun appRoleDao(): DAppRoleDao
    abstract fun userDao(): DUserDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<InitialWorker>().build()
                            WorkManager.getInstance().enqueue(request)
                        }
                    })
                    .build()
        }
    }
}