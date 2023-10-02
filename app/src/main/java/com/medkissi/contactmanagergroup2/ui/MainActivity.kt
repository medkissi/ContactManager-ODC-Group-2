package com.medkissi.contactmanagergroup2.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

import com.medkissi.contactmanagergroup2.R
import com.medkissi.contactmanagergroup2.adapters.ContactAdapter
import com.medkissi.contactmanagergroup2.adapters.OnContactClickListner
import com.medkissi.contactmanagergroup2.data.model.Contact
import com.medkissi.contactmanagergroup2.viewmodels.ContactViewModel
import java.security.Permission

const val CONTACT_TO_SAVE = "contact"
const val CONTACT_TO_UPDATE = "contact1"
const val UPDATED_CONTACT = "contact2"


class MainActivity : AppCompatActivity(), OnContactClickListner {
    private val readImagePermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) android.Manifest.permission.READ_MEDIA_IMAGES else android.Manifest.permission.READ_EXTERNAL_STORAGE
    val viewModel: ContactViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)
        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val adapter = ContactAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager


        val fab = findViewById<FloatingActionButton>(R.id.fab)
            .setOnClickListener {
                val intent = Intent(this, AddEditActivity::class.java)
                startActivityForResult(intent, 1)

            }


        if(ContextCompat.checkSelfPermission(this,readImagePermission)== PackageManager.PERMISSION_GRANTED){
            viewModel.getData().observe(this) { contacts ->
                adapter.submitList(contacts)

            }

        }else{

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val contact = data?.getSerializableExtra(CONTACT_TO_SAVE) as Contact
            viewModel.insertContact(contact)
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            val contact = data?.getSerializableExtra(UPDATED_CONTACT) as Contact
            viewModel.updateContact(contact)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all) {
            viewModel.deleteAllContact()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onItemClick(contact: Contact) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(CONTACT_TO_SAVE, contact)
        startActivity(intent)
    }




}