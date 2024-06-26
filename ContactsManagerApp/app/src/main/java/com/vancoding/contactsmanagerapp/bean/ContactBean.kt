package com.vancoding.contactsmanagerapp.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

// Each instance of this class represents a row in the table
@Entity(tableName = "contacts_table")
data class ContactBean(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val email : String
)
