package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.core.view.marginBottom
import kotlinx.android.synthetic.main.add_activity.*

@Suppress("DEPRECATION")
class AddActivity : AppCompatActivity() {
    companion object {
        const val SIZE_TAG: String = "TEL'S WIDTH"
    }

    lateinit var title: String
    lateinit var description: String
    lateinit var priority: String
    private var habitType: String = "Хорошая привычка"
    lateinit var quantity: String
    lateinit var period: String

    private var backIntent: Intent = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)

        backIntent.putExtra(MainActivity.ACTION, intent.getStringExtra(MainActivity.ACTION))

        when(intent.getStringExtra(MainActivity.ACTION)) {
            MainActivity.ADD -> {
                setTitle("Новая привычка")
                good_habit.isChecked = true
            }
            MainActivity.CHANGE -> {
                setTitle("Изменить привычку")
                title_edit.setText(intent.getStringExtra(MainActivity.TITLE))
                description_edit.setText(intent.getStringExtra(MainActivity.DESC))
                when (intent.getStringExtra(MainActivity.PRIOR)) {
                    "Низкий" -> priority_spinner.setSelection(0)
                    "Средний" -> priority_spinner.setSelection(1)
                    "Высокий" -> priority_spinner.setSelection(2)
                }
                when (intent.getStringExtra(MainActivity.TYPE)) {
                    good_habit.text -> good_habit.isChecked = true
                    bad_habit.text -> bad_habit.isChecked = true
                }
                period_edit.setText(intent.getStringExtra(MainActivity.PERIOD))
                quantity_edit.setText(intent.getStringExtra(MainActivity.QUANTITY))
            }
        }

        submit_habit.setOnClickListener {
            title = title_edit.text.toString()
            description = description_edit.text.toString()
            priority = priority_spinner.selectedItem.toString()
            habitType = if (good_habit.isChecked) good_habit.text.toString() else bad_habit.text.toString()
            period = if (period_edit.text.toString() != "") period_edit.text.toString() else "1"
            quantity = if (quantity_edit.text.toString() != "") quantity_edit.text.toString() else "1"

            backIntent.apply{
                putExtra(MainActivity.TITLE, title)
                putExtra(MainActivity.DESC, description)
                putExtra(MainActivity.PRIOR, priority)
                putExtra(MainActivity.TYPE, habitType)
                putExtra(MainActivity.QUANTITY, quantity)
                putExtra(MainActivity.PERIOD, period)
            }

            setResult(RESULT_OK, backIntent)
            finish()
        }
        makeColorPicker()
    }

    private fun makeColorPicker() {
        val width = windowManager.defaultDisplay.width;
        Log.d(SIZE_TAG, "the devise width is $width")
        for (view in ColorPicker){
            view.setOnClickListener { mainSquare.background = it.background }
            view.layoutParams.width = width / 8
            view.layoutParams.height = width / 8
            val params = view.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(width / 16, width / 12, width / 16, width / 12)
            view.layoutParams = params
        }
    }
}