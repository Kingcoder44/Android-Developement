package com.example.a7minutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

import com.example.a7minutesworkout.databinding.ItemHistoryBinding

class HistoryAdapter(private val items : ArrayList<String>): RecyclerView.Adapter<HistoryAdapter.viewHolder>() {

    class viewHolder(bining : ItemHistoryBinding) : RecyclerView.ViewHolder(bining.root){
        val llHistoryMain = bining.llHistoryItemMain
        val tvItem = bining.tvItem
        val tvPosition = bining.tvPosition

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
       return viewHolder(ItemHistoryBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,
           false
       ))
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val date : String = items.get(position)
        holder.tvPosition.text = (position+1).toString()
        holder.tvItem.text = date

        if (position % 2 == 0) {
            holder.llHistoryMain.setBackgroundColor(
                    Color.parseColor("#ebebeb")
                )
        } else {
            holder.llHistoryMain.setBackgroundColor(Color.parseColor("#ffffff"))
        }
    }
}