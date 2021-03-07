package com.example.habittracker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleView: TextView = itemView.findViewById(R.id.title)
    private val descriptionView: TextView = itemView.findViewById(R.id.description)
    private val priorityView: TextView = itemView.findViewById(R.id.priority)
    private val typeView: TextView = itemView.findViewById(R.id.type)
    //private val periodText: TextView = itemView.findViewById(R.id.period)
    //private val colorText: TextView = itemView.findViewById(R.id.color)

    fun bind(note: Note) {
        titleView.text = note.title
        descriptionView.text = note.description
        priorityView.text = note.priority
        typeView.text = note.type
        //periodText.text = note.period
        //colorText.text = note.color
    }
}