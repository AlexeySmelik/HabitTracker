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
        const val TITLE: String = "title"
        const val DESC: String = "description"
        const val PRIOR: String = "priority"
        const val TYPE: String = "type"
        const val PERIOD: String = "period"
        const val QUANTITY: String = "quantity"
        const val ACTION: String = "action"
        const val ADD: String = "add"
        const val CHANGE: String = "change"
        const val CLICK: String = "ON_CLICK"
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
                putExtra(ACTION, CHANGE)
                putExtra(TITLE, habit.title)
                putExtra(DESC, habit.description)
                putExtra(TYPE, habit.type)
                putExtra(PRIOR, habit.priority)
                putExtra(QUANTITY, habit.quantity)
                putExtra(PERIOD, habit.period)
            }
        Log.d(CLICK, "note on position num $position has been clicked")
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data ?: return
        if (data.getStringExtra(ACTION) == ADD) {
            if (data.getStringExtra(TITLE).isNullOrEmpty())
                return
            viewModel.addHabit(
                Habit(data.getStringExtra(TITLE)!!,
                    data.getStringExtra(DESC)!!,
                    data.getStringExtra(PRIOR)!!,
                    data.getStringExtra(TYPE)!!,
                    data.getStringExtra(PERIOD)!!,
                    data.getStringExtra(QUANTITY)!!))
        } else {
            if (data.getStringExtra(TITLE).isNullOrEmpty())
                viewModel.removeHabit()
            else
                viewModel.changeHabit(
                    Habit(data.getStringExtra(TITLE)!!,
                        data.getStringExtra(DESC)!!,
                        data.getStringExtra(PRIOR)!!,
                        data.getStringExtra(TYPE)!!,
                        data.getStringExtra(PERIOD)!!,
                        data.getStringExtra(QUANTITY)!!))
        }
        adapter.notifyDataSetChanged()
    }
}