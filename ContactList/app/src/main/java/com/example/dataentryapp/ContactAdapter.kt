package com.example.dataentryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ContactAdapter(private val listOfContacts: List<Contact>): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imgvw)
        val name: TextView = itemView.findViewById(R.id.txt1)
        val num: TextView = itemView.findViewById(R.id.subtxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfContacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currCont = listOfContacts[position]
        holder.name.text = currCont.name
        holder.num.text = currCont.number
        holder.img.setImageURI(currCont.img)
    }
}
