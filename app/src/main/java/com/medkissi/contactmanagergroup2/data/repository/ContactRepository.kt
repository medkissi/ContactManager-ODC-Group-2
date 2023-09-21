package com.medkissi.contactmanagergroup2.data.repository

import androidx.lifecycle.LiveData
import com.medkissi.contactmanagergroup2.ContactApp
import com.medkissi.contactmanagergroup2.data.datasource.ContactDatabase
import com.medkissi.contactmanagergroup2.data.model.Contact

class ContactRepository {
    private val dao =ContactDatabase.getDatabaseInstance(ContactApp.getAppContext()).dao()

    fun getContacts():LiveData<List<Contact>>{

        return dao.getContacts()
    }

    suspend fun insertContact(contact: Contact){
        dao.insertContact(contact)
    }
    suspend fun updateContact(contact: Contact){
        dao.updateContact(contact)
    }
    suspend fun deleteContact(contact: Contact){
        dao.deleteContact(contact)
    }

}