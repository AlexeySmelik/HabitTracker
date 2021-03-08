package com.example.habittracker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleView: TextView = itemView.findViewById(R.id.TitleView)
    private val descriptionView: TextView = itemView.findViewById(R.id.DescriptionView)
    private val priorityView: TextView = itemView.findViewById(R.id.PriorityView)
    private val typeView: TextView = itemView.findViewById(R.id.TypeView)
    private val frequencyView: TextView = itemView.findViewById(R.id.FrequencyView)

    fun bind(note: Note) {
        titleView.text = note.title
        descriptionView.text = note.description
        priorityView.text = note.priority
        typeView.text = note.type
        frequencyView.text = "${note.quantity} раз в ${note.period} дней"
    }
}