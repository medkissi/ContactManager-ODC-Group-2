package com.medkissi.contactmanagergroup2

import android.app.Application

class ContactApp:Application() {
    init {
        app =this

    }

    companion object{
        private lateinit var app:Application
        fun getAppContext() = app.applicationContext
    }
}