package com.example.habittracker.interfaces
import com.example.habittracker.HabitViewModel

interface HabitViewModelFromContext{
    fun getHabitViewModel(type: String): HabitViewModel
}