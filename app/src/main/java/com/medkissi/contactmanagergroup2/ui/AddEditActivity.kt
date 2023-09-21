package com.medkissi.contactmanagergroup2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import com.medkissi.contactmanagergroup2.R
import com.medkissi.contactmanagergroup2.data.model.Contact

class AddEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        val nom = findViewById<TextInputLayout>(R.id.text_nom)
        val tel = findViewById<TextInputLayout>(R.id.text_tel)
        val email = findViewById<TextInputLayout>(R.id.text_email)
        val btnSave= findViewById<Button>(R.id.btn_save)
            .setOnClickListener {
                if (nom.editText?.text.toString().isNotEmpty() &&
                    tel.editText?.text.toString().isNotEmpty() &&
                    email.editText?.text.toString().isNotEmpty() ){
                    val contact = Contact(
                        nomComplet = nom.editText?.text.toString(),
                        telephone = tel.editText?.text.toString(),
                        email = email.editText?.text.toString()
                        )
                    val intent = Intent(this,MainActivity::class.java)
                    intent.putExtra(CONTACT_KEY,contact)
                    setResult(RESULT_OK,intent)
                    finish()
                }
            }

    }
}