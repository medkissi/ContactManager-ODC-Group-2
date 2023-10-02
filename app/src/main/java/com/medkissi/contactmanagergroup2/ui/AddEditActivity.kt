package com.medkissi.contactmanagergroup2.ui

import android.annotation.SuppressLint
import android.app.Notification.Action
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputLayout
import com.medkissi.contactmanagergroup2.R
import com.medkissi.contactmanagergroup2.data.model.Contact


class AddEditActivity : AppCompatActivity() {
    lateinit var nom: TextInputLayout
    lateinit var tel: TextInputLayout
    lateinit var email: TextInputLayout
    var imgUri: Uri? = null
    lateinit var btnAddImage: ShapeableImageView
    val launcher = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        imgUri = it
        if (imgUri != null)
            btnAddImage.setImageURI(imgUri)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        nom = findViewById<TextInputLayout>(R.id.text_nom)
        tel = findViewById<TextInputLayout>(R.id.text_tel)
        email = findViewById<TextInputLayout>(R.id.text_email)


        val btnSave = findViewById<Button>(R.id.btn_save)
            .setOnClickListener {
                if (intent.hasExtra(CONTACT_TO_UPDATE)) {
                    updateContact()
                } else {
                    saveContact()
                }


            }
        btnAddImage = findViewById<ShapeableImageView>(R.id.add_image)
        btnAddImage.setOnClickListener {
            launcher.launch(
                PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        }

        if (intent.hasExtra(CONTACT_TO_UPDATE)) {
            val contact = intent.getSerializableExtra(CONTACT_TO_UPDATE) as Contact

            nom.editText?.setText(contact.nomComplet)
            tel.editText?.setText(contact.telephone)
            email.editText?.setText(contact.email)
            btnAddImage.setImageURI(Uri.parse(contact.image))
        }


    }


    private fun saveContact() {

        if (nom.editText?.text.toString().isNotEmpty() &&
            tel.editText?.text.toString().isNotEmpty() &&
            email.editText?.text.toString().isNotEmpty()

        ) {
            val contact = Contact(
                nomComplet = nom.editText?.text.toString(),
                telephone = tel.editText?.text.toString(),
                email = email.editText?.text.toString(),
                image = imgUri.toString()

            )
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(CONTACT_TO_SAVE, contact)
            setResult(RESULT_OK, intent)
            finish()

        }
    }


    private fun updateContact() {
        if (intent.hasExtra(CONTACT_TO_UPDATE)) {
            val data = intent.getSerializableExtra(CONTACT_TO_UPDATE) as Contact
            val contact = Contact(
                id = data.id,
                nomComplet = nom.editText?.text.toString(),
                email = email.editText?.text.toString(),
                telephone = tel.editText?.text.toString(),
                image = imgUri.toString()
            )
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(UPDATED_CONTACT, contact)
            setResult(RESULT_OK, intent)
            finish()

        }
    }


}


