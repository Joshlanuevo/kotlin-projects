package com.vancoding.contactsmanagerapp.db

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vancoding.contactsmanagerapp.bean.ContactBean

// DAO: Data Access Object, defines the methods to interact with DB
interface ContactDao {

    @Insert
    suspend fun insertContact(contact: ContactBean) : Long

    @Update
    suspend fun updateContact(contact: ContactBean)

    @Delete
    suspend fun deleteContact(contact: ContactBean)

    @Query("DELETE FROM contacts_table")
    suspend fun deteleAll()

    @Query("SELECT * FROM contacts_table")
    fun getAllContactsInDB() : LiveData<List<ContactBean>>

}