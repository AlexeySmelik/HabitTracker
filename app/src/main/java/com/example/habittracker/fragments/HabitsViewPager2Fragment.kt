package com.example.habittracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.habittracker.R
import com.example.habittracker.ViewPager2Adapter
import com.example.habittracker.interfaces.OnAddButtonClickListener
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.habits_view_pager2_fragment.*

class HabitsViewPager2Fragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.habits_view_pager2_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager2.adapter = ViewPager2Adapter(this)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = resources.getString(
                when (position) {
                    0 -> R.string.good_habit
                    1 -> R.string.bad_habit
                    else -> throw Exception("Error in HabitsViewFragment")
                }
            )
        }.attach()
        add_habit.setOnClickListener{ (context as OnAddButtonClickListener).onAddButtonClicked() }
    }
}