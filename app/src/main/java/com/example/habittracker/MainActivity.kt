package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
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
    }

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        add_habit.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
                .apply{ putExtra(ACTION, ADD) }
            startActivityForResult(intent, 1)
        }

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        listOfHabits.adapter = HabitsAdapter(this, viewModel.getHabits())
    }

    /*fun changeHabitOnClick(position: Int){
        val intent = Intent(this, AddActivity::class.java)
            .apply{
                putExtra(MainActivity.ACTION, MainActivity.CHANGE)
                putExtra(MainActivity.TITLE, viewModel.getHabit(position).title)
                putExtra(MainActivity.DESC, viewModel.getHabit(position).description)
                putExtra(MainActivity.TYPE, viewModel.getHabit(position).type)
                putExtra(MainActivity.QUANTITY, viewModel.getHabit(position).quantity)
                putExtra(MainActivity.PERIOD, viewModel.getHabit(position).period)
            }
        startActivityForResult(intent, 2)
    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data?.getStringExtra(TITLE).isNullOrEmpty()) return
        viewModel.addHabit(Note(
            data?.getStringExtra(TITLE)!!,
            data.getStringExtra(DESC)!!,
            data.getStringExtra(PRIOR)!!,
            data.getStringExtra(TYPE)!!,
            data.getStringExtra(PERIOD)!!,
            data.getStringExtra(QUANTITY)!!
        ))
    }
}