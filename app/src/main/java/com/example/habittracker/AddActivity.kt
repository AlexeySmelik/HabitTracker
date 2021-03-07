package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_activity.*

class AddActivity : AppCompatActivity() {
    companion object{
        const val TITLE: String = "title"
        const val DESC: String = "description"
        const val PRIOR: String = "priority"
        const val TYPE: String = "type"
        //const val PERIOD: String = "period"
        //const val COLOR: String = "color"
    }

    lateinit var title: String
    lateinit var description: String
    lateinit var priority: String
    private var habitType: String = "Хорошая привычка"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)
        good_habit.isChecked = true

        submit_habit.setOnClickListener {
            title = title_edit.text.toString()
            description = description_edit.text.toString()
            priority = priority_spinner.selectedItem.toString()
            habitType = if (good_habit.isChecked) good_habit.text.toString() else bad_habit.text.toString()

            val backIntent = Intent().apply {
                putExtra(TITLE, title)
                putExtra(DESC, description)
                putExtra(PRIOR, priority)
                putExtra(TYPE, habitType)
                //putExtra(PERIOD, "")
                //putExtra(COLOR, "zopa")
            }
            setResult(RESULT_OK, backIntent)
            finish()
        }
    }
}