package com.vancoding.contactlistapp.repository

import android.util.Log
import com.vancoding.contactlistapp.api.ApiUrl
import com.vancoding.contactlistapp.bean.UsersBean
import com.vancoding.contactlistapp.db.UsersDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @Reference
 * @source : Udemy
 * @author : abbas masri
 */
class UsersRepository(private val usersApi: ApiUrl, private val usersDao: UsersDao) {
    suspend fun getUsersList(): List<UsersBean> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = usersApi.getUsersList().execute()
            if (response.isSuccessful) {
                val users = response.body()?.data ?: emptyList()
                usersDao.insertAll(users)
                users
            } else {
                usersDao.getAllUsers()
            }
        } catch (e: Exception) {
            usersDao.getAllUsers()
        }
    }
}
