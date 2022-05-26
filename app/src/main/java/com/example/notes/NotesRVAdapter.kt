package com.example.notes

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(val context: Context,val listener:INotesRVAdapter):RecyclerView.Adapter<NotesRVAdapter.Noteviewholder>() {
    val allNotes = ArrayList<Notes>()
    inner class Noteviewholder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val delbutton = itemView.findViewById<ImageView>(R.id.imageView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Noteviewholder {
        val viewHolder=Noteviewholder(LayoutInflater.from(context).inflate(R.layout.activity_items,parent,false))
        viewHolder.delbutton.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: Noteviewholder, position: Int) {
        val currentNote =allNotes[position]
        holder.textView.text=currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList:List<Notes>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}
interface INotesRVAdapter{
    fun onItemClicked(notes: Notes)
}