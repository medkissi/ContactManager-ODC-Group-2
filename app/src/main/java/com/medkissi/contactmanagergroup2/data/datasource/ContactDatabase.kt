package com.medkissi.contactmanagergroup2.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.medkissi.contactmanagergroup2.data.model.Contact

@Database(entities = [Contact::class], version = 2)
abstract class ContactDatabase:RoomDatabase() {
    abstract fun dao():ContactDao

    companion object {
        private var INSTANCE: ContactDatabase? = null
        fun getDatabaseInstance(context: Context): ContactDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contact_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE!!
        }
    }
}