package com.example.habittracker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleText: TextView = itemView.findViewById(R.id.title)
    private val descriptionText: TextView = itemView.findViewById(R.id.description)
    //private val priorityText: TextView = itemView.findViewById(R.id.priority)
    //private val typeText: TextView = itemView.findViewById(R.id.type)
    //private val periodText: TextView = itemView.findViewById(R.id.period)
    //private val colorText: TextView = itemView.findViewById(R.id.color)

    fun bind(note: Note) {
        titleText.text = note.title
        descriptionText.text = note.description
        //priorityText.text = note.priority
        //typeText.text = note.type
        //periodText.text = note.period
        //colorText.text = note.color
    }
}