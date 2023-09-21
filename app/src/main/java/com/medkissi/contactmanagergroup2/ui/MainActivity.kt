package com.medkissi.contactmanagergroup2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.medkissi.contactmanagergroup2.R
import com.medkissi.contactmanagergroup2.adapters.ContactAdapter
import com.medkissi.contactmanagergroup2.adapters.OnContactClickListner
import com.medkissi.contactmanagergroup2.data.model.Contact
import com.medkissi.contactmanagergroup2.viewmodels.ContactViewModel

const val  CONTACT_KEY ="contact"
class MainActivity : AppCompatActivity(), OnContactClickListner {
    val viewModel:ContactViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        val adapter = ContactAdapter(this)
        val layoutManager = LinearLayoutManager(this )
        recyclerView.adapter = adapter
        recyclerView.layoutManager =  layoutManager
        viewModel.getData().observe(this){
            adapter.submitList(it)

        }

        val fab =  findViewById<FloatingActionButton>(R.id.fab)
            .setOnClickListener {
                val intent =Intent(this,AddEditActivity::class.java)
                startActivityForResult(intent,2)

            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode ==2 && resultCode == RESULT_OK){
          val   contact = data?.getSerializableExtra(CONTACT_KEY) as Contact
            viewModel.insertContact(contact)
        }
    }

    override fun onItemClick(contact: Contact) {
        TODO("Not yet implemented")
    }

    override fun onDeleteClick(contact: Contact) {
       viewModel.deleteContact(contact)
    }

    override fun onUpdateClick(contact: Contact) {
        TODO("Not yet implemented")
    }
}