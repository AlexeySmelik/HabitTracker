package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_activity.*

class AddActivity : AppCompatActivity() {
    companion object{
        const val TITLE: String = "title"
        const val DESC: String = "description"
        //const val PRIOR: String = "priority"
        //const val TYPE: String = "type"
        //const val PERIOD: String = "period"
        //const val COLOR: String = "color"
    }

    private var title: String = "Пусто"
    private var description: String = "Пусто"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)

        submit_habit.setOnClickListener {
            title = title_edit.text.toString()
            description = description_edit.text.toString()
            val backIntent = Intent().apply {
                putExtra(TITLE, title)
                putExtra(DESC, description)
                //putExtra(PRIOR, "12")
                //putExtra(TYPE, "e312")
                //putExtra(PERIOD, "")
                //putExtra(COLOR, "zopa")
            }
            setResult(RESULT_OK, backIntent)
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(TITLE, title)
        outState.putString(DESC, description)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        title = savedInstanceState.getString(TITLE)!!
        description = savedInstanceState.getString(DESC)!!
    }
}