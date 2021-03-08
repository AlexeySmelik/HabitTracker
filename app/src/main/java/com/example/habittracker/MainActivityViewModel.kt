package com.example.habittracker

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private var habits: MutableList<Habit> = mutableListOf()

   private var positionToChangeHabit: Int = -1


    fun addHabit(habit: Habit){
        habits.add(habit)
    }

    fun getHabits() : MutableList<Habit> = habits

    fun changeHabit(habit: Habit) {
        habits[positionToChangeHabit] = habit
    }

    fun removeHabit() {
        habits.removeAt(positionToChangeHabit)
    }

    fun setPositionToChangeHabit(position: Int) {
        positionToChangeHabit = position
    }
}