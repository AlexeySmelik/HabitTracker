package com.example.habittracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


interface OnItemClickListener {
    fun onItemClicked(habit: Habit, position: Int)
}

class HabitsAdapter(private var habits: MutableList<Habit>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<HabitsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitsViewHolder {
        return HabitsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.habit, parent, false))
    }

    override fun onBindViewHolder(holderHabits: HabitsViewHolder, position: Int) {
        holderHabits.bind(habits[position], itemClickListener, position)
    }

    override fun getItemCount(): Int = habits.size
}

class HabitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleView: TextView = itemView.findViewById(R.id.TitleView)
    private val descriptionView: TextView = itemView.findViewById(R.id.DescriptionView)
    private val priorityView: TextView = itemView.findViewById(R.id.PriorityView)
    private val typeView: TextView = itemView.findViewById(R.id.TypeView)
    private val frequencyView: TextView = itemView.findViewById(R.id.FrequencyView)

    fun bind(habit: Habit, clickListener: OnItemClickListener, position: Int) {
        titleView.text = habit.title
        descriptionView.text = habit.description
        priorityView.text = habit.priority
        typeView.text = habit.type
        frequencyView.text = "${habit.quantity} раз в ${habit.period} дней"
        itemView.setOnClickListener { clickListener.onItemClicked(habit, position) }
    }
}