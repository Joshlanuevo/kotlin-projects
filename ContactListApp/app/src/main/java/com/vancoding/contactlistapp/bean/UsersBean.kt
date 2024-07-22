package com.vancoding.contactlistapp.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UsersBean(
    @PrimaryKey
    val id: Int = 0,
    val email: String = "",
    val first_name: String = "",
    val last_name: String = "",
    val avatar: String = ""
)

data class UserResponse(
    val data: List<UsersBean>
)
