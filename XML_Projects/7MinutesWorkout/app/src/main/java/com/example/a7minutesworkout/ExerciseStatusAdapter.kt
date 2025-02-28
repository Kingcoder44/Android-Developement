package com.example.a7minutesworkout

import android.graphics.Color
import android.provider.CalendarContract.Colors
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val items : ArrayList<ExerciseModel> ) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>(){


        class ViewHolder(binding : ItemExerciseStatusBinding) :
            RecyclerView.ViewHolder(binding.root)
        {
                val tvItem = binding.tvItem
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context), parent,false))


    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model : ExerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()

        when{
            model.getisSelected() ->{
                //to get context to change color background we use cotnext compat
                        holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,
                            R.drawable.item_selectpr_selected_bkg)
            }
            model.getisCompleted()->{
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circular_accent_bkg)
                    holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else -> {
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circular_gray_bkg)
            }
        }
    }
}