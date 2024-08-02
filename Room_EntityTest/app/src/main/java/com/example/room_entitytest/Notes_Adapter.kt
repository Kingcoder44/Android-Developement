package com.example.room_entitytest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Update
import com.example.room_entitytest.db.Note

class Notes_Adapter(
    private val listOfNote : List<Note>,
    private val clickListener: ClickListener
):RecyclerView.Adapter<Notes_Adapter.NoteViewHolder>() {
    class NoteViewHolder(item : View) : RecyclerView.ViewHolder(item)
    {
        var textName : TextView = item.findViewById(R.id.nt_nm)
        val textContn : TextView = item.findViewById(R.id.nt_cntnt)
        val del : ImageView = item.findViewById(R.id.dlt_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_note,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfNote.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val curr_Note = listOfNote[position]
        holder.textName.text=curr_Note.notename
        holder.textContn.text = curr_Note.notecont
        holder.itemView.setOnClickListener{
            clickListener.updateNote(curr_Note)
        }
        holder.del.setOnClickListener {
            clickListener.deleteNote(curr_Note)
        }
    }
    //we will create an interface whihc will act as a communicator between adapter and main actiovity
    interface ClickListener {
        fun  updateNote(note:Note)
        fun deleteNote(note:Note)
    }
}