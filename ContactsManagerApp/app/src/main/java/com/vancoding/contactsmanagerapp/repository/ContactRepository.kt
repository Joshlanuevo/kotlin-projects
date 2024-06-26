package com.vancoding.contactsmanagerapp.repository

import com.vancoding.contactsmanagerapp.bean.ContactBean
import com.vancoding.contactsmanagerapp.db.ContactDao

class ContactRepository(private val contactDao: ContactDao) {

    val contacts = contactDao.getAllContactsInDB();

    suspend fun insert(contact: ContactBean): Long {
        return contactDao.insertContact(contact);
    }

    suspend fun update(contact: ContactBean) {
        return contactDao.updateContact(contact);
    }

    suspend fun delete(contact: ContactBean) {
        return contactDao.deleteContact(contact);
    }

    suspend fun deleteAll() {
        return contactDao.deteleAll();
    }
}