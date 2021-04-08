package com.example.habittracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.habittracker.Habit
import com.example.habittracker.HabitViewModel
import com.example.habittracker.HabitsAdapter
import com.example.habittracker.R
import com.example.habittracker.interfaces.HabitViewModelFromContext
import com.example.habittracker.interfaces.OnItemClickListener
import kotlinx.android.synthetic.main.habits_recycler_view_fragment.*

class HabitRecyclerViewFragment : Fragment(){
    companion object{
        const val HABIT_TYPE = "habit_type"

        fun newInstance(type: String): HabitRecyclerViewFragment =
            HabitRecyclerViewFragment().apply{
                arguments = Bundle().apply{
                    putString(HABIT_TYPE, type)
                }
            }
    }

    private lateinit var viewModel: HabitViewModel
    private var habitsOfCertainType = arrayListOf<Habit>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.habits_recycler_view_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type = arguments?.getString(HABIT_TYPE)
        viewModel= (context as HabitViewModelFromContext).getHabitViewModel(type!!)
        habitsOfCertainType = ArrayList(viewModel.getHabits().filter{ habit -> habit.type == type })
        listOfHabits.adapter = HabitsAdapter(habitsOfCertainType, context as OnItemClickListener)
    }
}