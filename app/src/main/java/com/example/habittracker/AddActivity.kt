package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)
        good_habit.isChecked = true

        /*radio_group_type_habit.setOnCheckedChangeListener{
                RadioGroup: RadioGroup, i: Int ->
            val selectedRadioButton: RadioButton = findViewById(RadioGroup.checkedRadioButtonId)
            type = selectedRadioButton.text.toString()
        }*/

        submit_habit.setOnClickListener {
            title = title_edit.text.toString()
            description = description_edit.text.toString()
            priority = priority_spinner.selectedItem.toString()

            val backIntent = Intent().apply {
                putExtra(TITLE, title)
                putExtra(DESC, description)
                putExtra(PRIOR, priority)
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
        outState.putString(PRIOR, priority)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        title = savedInstanceState.getString(TITLE)!!
        description = savedInstanceState.getString(DESC)!!
        priority = savedInstanceState.getString(PRIOR)!!
    }
}