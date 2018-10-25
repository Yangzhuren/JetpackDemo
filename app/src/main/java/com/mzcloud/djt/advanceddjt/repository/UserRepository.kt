package com.mzcloud.djt.advanceddjt.repository

import androidx.lifecycle.LiveData
import com.mzcloud.djt.advanceddjt.dao.DUserDao

class UserRepository private constructor(private val userDao: DUserDao) {
    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: DUserDao) = instance ?: synchronized(this) {
            instance ?: UserRepository(userDao).also { instance = it }
        }
    }

    fun getUserNames(): LiveData<List<String>> = userDao.getUserNames()
}