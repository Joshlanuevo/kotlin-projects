package com.vancoding.contactlistapp.bean
data class UsersBean(
    val id: Int,
    val email: String,
    val firstname: String,
    val lastname: String,
    val avatar: String
)

data class UserResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<UsersBean>
)
