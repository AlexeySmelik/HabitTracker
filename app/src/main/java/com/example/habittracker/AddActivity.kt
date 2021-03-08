package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_activity.*

class AddActivity : AppCompatActivity() {
    lateinit var title: String
    lateinit var description: String
    lateinit var priority: String
    private var habitType: String = "Хорошая привычка"
    lateinit var quantity: String
    lateinit var period: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)
        good_habit.isChecked = true

        submit_habit.setOnClickListener {
            title = title_edit.text.toString()
            description = description_edit.text.toString()
            priority = priority_spinner.selectedItem.toString()
            habitType = if (good_habit.isChecked) good_habit.text.toString() else bad_habit.text.toString()
            period = period_edit.text.toString()
            quantity = quantity_edit.text.toString()

            val backIntent = Intent().apply {
                putExtra(MainActivity.TITLE, title)
                putExtra(MainActivity.DESC, description)
                putExtra(MainActivity.PRIOR, priority)
                putExtra(MainActivity.TYPE, habitType)
                putExtra(MainActivity.QUAN, quantity)
                putExtra(MainActivity.PERIOD, period)
            }
            setResult(RESULT_OK, backIntent)
            finish()
        }
    }
}