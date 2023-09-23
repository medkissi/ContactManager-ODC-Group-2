package com.medkissi.contactmanagergroup2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.medkissi.contactmanagergroup2.R
import com.medkissi.contactmanagergroup2.data.model.Contact

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val nom = findViewById<TextView>(R.id.textview_nom)
        val tel = findViewById<TextView>(R.id.textView_tel)
        val email = findViewById<TextView>(R.id.textView_email)
        val contact = intent.getSerializableExtra(CONTACT_TO_SAVE) as Contact

        val toolbar = findViewById<Toolbar>(R.id.topAppBar)
            .setNavigationOnClickListener {
                finish()
            }

        nom.text = contact.nomComplet
        tel.text = contact.telephone
        email.text = contact.email

    }
}