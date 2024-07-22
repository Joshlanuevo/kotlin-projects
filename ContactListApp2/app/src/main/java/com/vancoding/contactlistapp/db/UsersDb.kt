package com.vancoding.contactlistapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vancoding.contactlistapp.bean.UsersBean

/**
 * @Reference
 * @source : Udemy
 * @author : abbas masri
 */

@Database(entities = [UsersBean::class], version = 1)
abstract class UsersDb : RoomDatabase() {
    abstract fun usersDao() : UsersDao

    companion object {
        private var instance: UsersDb? = null

        fun getInstance(context: Context): UsersDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): UsersDb {
            return Room.databaseBuilder(
                context.applicationContext,
                UsersDb::class.java,
                "users_db"
            ).build()
        }
    }
}