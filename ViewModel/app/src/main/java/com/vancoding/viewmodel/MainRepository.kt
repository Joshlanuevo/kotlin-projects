package com.vancoding.viewmodel

import com.vancoding.viewmodel.model.User
import kotlinx.coroutines.delay

class MainRepository {

    suspend fun getUsers() : List<User> {
        delay(8000)
        val users : List<User> = listOf(
            User(1, "Steph"),
            User(1, "Lebron"),
            User(1, "Kobe"),
            User(1, "Kevin"),
            User(1, "Jordan")
        )
        return users
    }
}