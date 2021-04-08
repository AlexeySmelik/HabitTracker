package com.example.habittracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(), HabitViewModelFromContext, AddOrChangeHabitCallback, OnItemClickListener,
OnAddButtonClickedListener {
    companion object {
        private const val ARG_NAME = "arg_name"
        private const val ADD: String = "add_habit"
        private const val ARG_INFO = "arg_info"
        private const val CHANGE: String = "change_habit"
    }

    private lateinit var habitViewModel: HabitViewModel
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp() =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    override fun getHabitViewModel(type: String) : HabitViewModel = habitViewModel

    private fun onAddHabit(habit: Habit){
        if (habit.title.isNotEmpty()) {
            habitViewModel.addHabit(habit)
        }
    }

    private fun onChangeHabit(habit: Habit) = if (habit.title.isEmpty()) {
        habitViewModel.removeHabit()
    } else {
        habitViewModel.changeHabit(habit)
    }

    override fun onSaveHabit(habit: Habit, fragmentName: String) {
        when (fragmentName) {
            ADD -> onAddHabit(habit)
            CHANGE -> onChangeHabit(habit)
        }
        navController.navigate(R.id.habitsViewPager2Fragment)
    }

    override fun onItemClicked(habit: Habit, position: Int) {
        habitViewModel.setPositionToChangeHabit(position)
        navController.navigate(R.id.addOrChangeFragment,
            Bundle().apply {
                putParcelable(ARG_INFO, habit)
                putString(ARG_NAME, CHANGE)
            })
    }

    override fun onAddButtonClicked() {
        navController.navigate(R.id.addOrChangeFragment,
        Bundle().apply {
            putString(ARG_NAME, ADD)
        })
    }
}