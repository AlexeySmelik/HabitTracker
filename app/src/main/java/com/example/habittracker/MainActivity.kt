package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(), OnItemClickListener {
    companion object{
        const val ACTION: String = "action"
        const val ADD: String = "add"
        const val CHANGE: String = "change"
        const val CLICK: String = "ON_CLICK"
        const val COLOR: String = "color"
        const val HABIT: String = "habit"
    }

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: HabitsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        add_habit.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
                .apply{ putExtra(ACTION, ADD) }
            startActivityForResult(intent, 1)
        }

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        adapter = HabitsAdapter(viewModel.getHabits(), this)

        listOfHabits.adapter = adapter
    }

    override fun onItemClicked(habit: Habit, position: Int) {
        viewModel.setPositionToChangeHabit(position)
        val intent = Intent(this, AddActivity::class.java)
            .apply{
                putExtra(HABIT, habit)
                putExtra(ACTION, CHANGE)
            }
        Log.d(CLICK, "note on position num $position has been clicked")
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data ?: return
        val habit = data.getParcelableExtra<Habit>(HABIT)!!

        if (data.getStringExtra(ACTION) == ADD) {
            if (!onAddHabit(habit)) return
        }
        else if (data.getStringExtra(ACTION) == CHANGE) {
            onSomeHabitWasChanged(habit)
        }
    }

    private fun onAddHabit(habit: Habit): Boolean {
        if (habit.title.isNullOrEmpty()) {
            return false
        } else {
            viewModel.addHabit(habit)
            adapter.notifyDataSetChanged()
        }
        return true
    }

    private fun onSomeHabitWasChanged(habit: Habit) {
        if (habit.title.isNullOrEmpty()) {
            viewModel.removeHabit()
        } else {
            viewModel.changeHabit(habit)
        }
        adapter.notifyDataSetChanged()
    }
}