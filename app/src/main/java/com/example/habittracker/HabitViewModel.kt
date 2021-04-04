package com.example.habittracker

import androidx.lifecycle.ViewModel

class HabitViewModel : ViewModel() {
    private var habits: MutableList<Habit> = mutableListOf()

    fun addHabit(habit: Habit){
        habits.add(habit)
    }

    fun getHabits() : MutableList<Habit> = habits

    fun changeHabit(habit: Habit, position: Int) {
        habits[position] = habit
    }

    fun removeHabit(position: Int) {
        habits.removeAt(position)
    }
}