package com.example.habittracker

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private var habits: MutableList<Note> = mutableListOf()

    fun addHabit(note: Note){
        habits.add(note)
    }

    fun getHabits() : MutableList<Note> = habits

    fun changeHabit(note: Note, position: Int){
        habits[position] = note
    }

    fun getHabit(position: Int): Note = habits[position]
}