package com.vancoding.contactlistapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vancoding.contactlistapp.bean.UsersBean

@Dao
interface UsersDao {
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UsersBean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UsersBean>)
}