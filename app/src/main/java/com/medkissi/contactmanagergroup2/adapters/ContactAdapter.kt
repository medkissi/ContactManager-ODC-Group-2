package com.medkissi.contactmanagergroup2.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.medkissi.contactmanagergroup2.R
import com.medkissi.contactmanagergroup2.data.model.Contact

class ContactAdapter(
    val listner: OnContactClickListner
) : ListAdapter<Contact, ContactAdapter.ContactViewHolder>(ContactDiff()) {

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView.rootView) {
        val nomComplet = itemView.findViewById<TextView>(R.id.nom)
        val telephone = itemView.findViewById<TextView>(R.id.textView2)
        val image = itemView.findViewById<ShapeableImageView>(R.id.photo)


        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val contact = getItem(adapterPosition)
                    listner.onItemClick(contact)
                }
            }
        }

        fun bind(contact: Contact) {
            nomComplet.text = contact.nomComplet
            telephone.text = contact.telephone
            image.setImageURI(Uri.parse(contact.image))

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact)
    }

}

class ContactDiff : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }

}

interface OnContactClickListner {
    fun onItemClick(contact: Contact)

}
