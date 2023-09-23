package com.medkissi.contactmanagergroup2.data.datasource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.medkissi.contactmanagergroup2.data.model.Contact

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact_table")
    fun getContacts(): LiveData<List<Contact>>
    @Insert
    suspend fun insertContact(contact: Contact)
    @Delete
    suspend fun deleteContact(contact: Contact)
    @Update
    suspend fun updateContact(contact: Contact)
    @Query("DELETE FROM contact_table")
    suspend fun deleteAllContact()

}