package com.medkissi.contactmanagergroup2.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medkissi.contactmanagergroup2.data.model.Contact
import com.medkissi.contactmanagergroup2.data.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactViewModel:ViewModel() {
    private val repository =ContactRepository()

    fun getData():LiveData<List<Contact>>{
        return repository.getContacts()
    }

    fun deleteContact(contact: Contact){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.deleteContact(contact)
            }
        }

    }
    fun insertContact(contact: Contact){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.insertContact(contact)
            }

        }
    }
}