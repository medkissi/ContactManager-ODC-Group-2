package com.medkissi.contactmanagergroup2.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true) var id :Int = 0,
    @ColumnInfo(name = "nom_complet") val nomComplet:String,
    val telephone:String,
    val email:String,
    val image:String

): Serializable