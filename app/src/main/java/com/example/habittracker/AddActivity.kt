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
        const val PERIOD: String = "period"
        const val COLOR: String = "color"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)

        submit_habit.setOnClickListener {
            val backIntent = Intent()
            backIntent.putExtra(TITLE, "title")
            backIntent.putExtra(DESC, "des")
            backIntent.putExtra(PRIOR, "12")
            backIntent.putExtra(TYPE, "e312")
            backIntent.putExtra(PERIOD, "")
            backIntent.putExtra(COLOR, "zopa")
            setResult(RESULT_OK, backIntent)
            finish()
        }
    }
}