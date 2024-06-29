package com.vancoding.contactsmanagerapp.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

// Each instance of this class represents a row in the table
@Entity(tableName = "contacts_table")
data class ContactBean(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var name : String,
    var email : String
)
