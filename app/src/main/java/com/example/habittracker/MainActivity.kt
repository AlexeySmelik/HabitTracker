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
        const val PERIOD: String = "period"
        const val COLOR: String = "color"
    }

    private var habits: MutableList<Note> = mutableListOf(
        Note("Покурить кальян", " Самый забивной", " HIGH", " TODAY", " No", " Red"),
        Note("Покурить кальян", " Самый забивной", " HIGH", " TODAY", " No", " Blue"),
        Note("Покурить кальян", " Самый забивной", " HIGH", " TODAY", " No", " Green"),
        Note("Покурить кальян", " Самый забивной", " HIGH", " TODAY", " No", " Yellow")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        add_habit.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onResume() {
        super.onResume()
        listOfHabits.adapter = HabitsAdapter(habits)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        habits.add(Note(
            data?.getStringExtra(TITLE) ?: "Пустая заметка",
            data?.getStringExtra(DESC) ?: "Пусто",
            data?.getStringExtra(PRIOR) ?: "Medium",
            data?.getStringExtra(TYPE) ?: "Empty",
            data?.getStringExtra(PERIOD) ?: "-",
            data?.getStringExtra(COLOR) ?: "Gray"
        ))
    }
}