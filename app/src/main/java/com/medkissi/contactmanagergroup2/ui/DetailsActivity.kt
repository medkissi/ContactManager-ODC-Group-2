package com.medkissi.contactmanagergroup2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        toolbar.setNavigationOnClickListener {
            finish()
        }
        setSupportActionBar(toolbar)

        nom.text = contact.nomComplet
        tel.text = contact.telephone
        email.text = contact.email

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.update -> {

            }

            R.id.delte -> {


            }
            R.id.share -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}