package com.example.habittracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.main_fragment.*

class HabitListFragment: Fragment(), OnItemClickListener, AddOrChangeHabitCallback {
    companion object{
        const val ADD: String = "add_habit"
        const val CHANGE: String = "change_habit"

        private const val ARG_NAME = "arg_name"

        fun newInstance(name: String): HabitListFragment =
            HabitListFragment().apply{
                arguments = Bundle().apply{
                    putString(ARG_NAME, name)
                }
            }
    }

    var name = ""

    private lateinit var viewModel: HabitViewModel
    private lateinit var adapter: HabitsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.main_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_NAME, "")
        }

        viewModel = ViewModelProvider(this).get(HabitViewModel::class.java)
        adapter = HabitsAdapter(viewModel.getHabits(), this)
        listOfHabits.adapter = adapter

        add_habit.setOnClickListener {
            val addHabitFragment = AddOrChangeHabitFragment.newInstance(ADD)
            childFragmentManager
                .beginTransaction()
                .add(R.id.add_or_change_fragment_container, addHabitFragment)
                .commit()
        }
    }

    override fun onItemClicked(habit: Habit, position: Int) {
        val changeHabitFragment = AddOrChangeHabitFragment.newInstance(CHANGE, habit)
        viewModel.setPositionToChangeHabit(position)
        childFragmentManager
            .beginTransaction()
            .add(R.id.add_or_change_fragment_container, changeHabitFragment)
            .commit()
    }

    private fun onAddHabit(habit: Habit){
        if (!habit.title.isNullOrEmpty()) {
            viewModel.addHabit(habit)
            adapter.notifyDataSetChanged()
        }
    }

    private fun onChangeHabit(habit: Habit){
        if (habit.title.isNullOrEmpty()) {
            viewModel.removeHabit()
        } else {
            viewModel.changeHabit(habit)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onSaveHabit(habit: Habit, fragment: AddOrChangeHabitFragment) {
        when (fragment.name) {
            ADD -> onAddHabit(habit)
            CHANGE -> onChangeHabit(habit)
        }

        childFragmentManager
            .beginTransaction()
            .remove(fragment)
            .commit()
    }
}