package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    companion object{
        const val TITLE: String = "title"
        const val DESC: String = "description"
        const val PRIOR: String = "priority"
        const val TYPE: String = "type"
        //const val PERIOD: String = "period"
        //const val COLOR: String = "color"
    }

    private var habits: MutableList<Note> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        add_habit.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, 1)
        }

        listOfHabits.adapter = HabitsAdapter(habits)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.getStringExtra(TITLE) ?: return
        habits.add(Note(
            data.getStringExtra(TITLE)!!,
            data.getStringExtra(DESC)!!,
            data.getStringExtra(PRIOR)!!,
            //data?.getStringExtra(PERIOD) ?: "-",
            //data?.getStringExtra(COLOR) ?: "Gray"
        ))
        listOfHabits.adapter = HabitsAdapter(habits)
    }
}