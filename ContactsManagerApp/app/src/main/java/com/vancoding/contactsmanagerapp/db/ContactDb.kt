package com.vancoding.contactsmanagerapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vancoding.contactsmanagerapp.bean.ContactBean
import kotlin.concurrent.Volatile

@Database(entities = [ContactBean::class], version = 1)
abstract class ContactDb : RoomDatabase() {

    abstract val contactDao: ContactDao;

    // Singleton Design Pattern
    // only one instance of the database exists, avoiding
    // unnecessary overhead associated with repeated database creation

    // companion object: define a static singleton instance of this DB Class
    companion object {
        @Volatile
        private var INSTANCE: ContactDb ?= null;

        fun getInstance(context: Context): ContactDb {
            synchronized(this) {

                var instance = INSTANCE;
                if (instance == null) {
                    // Creating the database object
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDb::class.java,
                        "contacts_db"
                    ).build()
                }
                INSTANCE = instance;
                return instance;
            }
        }
    }
}