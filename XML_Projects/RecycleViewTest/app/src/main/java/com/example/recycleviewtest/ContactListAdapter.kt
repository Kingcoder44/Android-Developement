package com.example.recycleviewtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactListAdapter(
    private val listOfContacts: List<ContactItem>
) : RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder>() {

    class ContactListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgvw)
        val ht: TextView = view.findViewById(R.id.txt1)
        val subhd: TextView = view.findViewById(R.id.subtxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_rv_item, parent, false)
        return ContactListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfContacts.size
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        holder.img.setImageResource(listOfContacts[position].imgres)
        holder.ht.text = listOfContacts[position].headtxt
        holder.subhd.text = listOfContacts[position].subheadtxt
    }
}
