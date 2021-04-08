package com.example.habittracker.interfaces

import com.example.habittracker.Habit

interface OnItemClickListener {
    fun onItemClicked(habit: Habit, position: Int)
}