package com.vancoding.contactsmanagerapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vancoding.contactsmanagerapp.bean.ContactBean

@Database(entities = [ContactBean::class], version = 1)
abstract class ContactDb : RoomDatabase() {

    abstract val contactDao: ContactDao

    // Singleton Design Pattern

}