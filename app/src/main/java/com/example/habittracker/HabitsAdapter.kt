package com.example.habittracker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HabitsAdapter(private val myContext: Context, private var notes: MutableList<Note>) : RecyclerView.Adapter<HabitsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitsViewHolder {
        return HabitsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.habit, parent, false))
    }

    override fun onBindViewHolder(holderHabits: HabitsViewHolder, position: Int) {
        holderHabits.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size
}