package com.example.habittracker

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.habittracker.fragments.HabitRecyclerViewFragment
import java.lang.Exception

class ViewPager2Adapter(fragment: Fragment) : FragmentStateAdapter(fragment)  {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> HabitRecyclerViewFragment.newInstance("Хорошая привычка")
        1 -> HabitRecyclerViewFragment.newInstance("Плохая привычка")
        else -> throw Exception("Habit's type error")
    }
}