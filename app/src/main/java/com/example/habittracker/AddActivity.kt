package com.example.habittracker

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import kotlinx.android.synthetic.main.add_activity.*

@Suppress("DEPRECATION")
class AddActivity : AppCompatActivity() {
    companion object {
        const val SIZE_TAG: String = "COLOR PICKER SIZES"
    }

    lateinit var title: String
    lateinit var description: String
    lateinit var priority: String
    lateinit var quantity: String
    lateinit var period: String

    private var habitType: String = "Хорошая привычка"
    private var color: Int = Color.BLACK
    private var colorOfSquares: MutableList<Int> = mutableListOf()
    private var backIntent: Intent = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)

        backIntent.putExtra(MainActivity.ACTION, intent.getStringExtra(MainActivity.ACTION))

        when(intent.getStringExtra(MainActivity.ACTION)) {
            MainActivity.ADD -> setDefaultParams()
            MainActivity.CHANGE -> restoreParams()
        }

        submit_habit.setOnClickListener { saveHabit() }

        makeColorPicker()
    }

    private fun setDefaultParams() {
        setTitle("Новая привычка")
        good_habit.isChecked = true
    }

    private fun restoreParams() {
        setTitle("Изменить привычку")
        val habit = intent.getParcelableExtra<Habit>(MainActivity.HABIT)!!

        title_edit.setText(habit.title)
        description_edit.setText(habit.description)

        when (habit.priority) {
            "Низкий" -> priority_spinner.setSelection(0)
            "Средний" -> priority_spinner.setSelection(1)
            "Высокий" -> priority_spinner.setSelection(2)
        }

        when (habit.type) {
            good_habit.text -> good_habit.isChecked = true
            bad_habit.text -> bad_habit.isChecked = true
        }

        period_edit.setText(habit.period)
        quantity_edit.setText(habit.quantity)
        mainSquare.setBackgroundColor(habit.color)
    }

    private fun saveHabit() {
        backIntent.apply{ putExtra(MainActivity.HABIT, makeHabit()) }

        setResult(RESULT_OK, backIntent)
        finish()
    }

    private fun makeHabit(): Habit {
        title = title_edit.text.toString()
        description = description_edit.text.toString()
        priority = priority_spinner.selectedItem.toString()
        habitType = if (good_habit.isChecked) good_habit.text.toString() else bad_habit.text.toString()
        period = if (period_edit.text.toString() != "") period_edit.text.toString() else "1"
        quantity = if (quantity_edit.text.toString() != "") quantity_edit.text.toString() else "1"
        return Habit(title, description, priority, habitType, period, quantity, color)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(MainActivity.COLOR, color)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        color = savedInstanceState.getInt(MainActivity.COLOR)
        mainSquare.setBackgroundColor(color)
    }

    private fun makeColorPicker() {
        val width = windowManager.defaultDisplay.width
        val squareSideSize = width / 8
        val squareMarginSize = width / 12

        Log.d(SIZE_TAG, "the devise width is $width")

        val gradientBitmap: Bitmap = Bitmap.createBitmap(
            (2 * squareMarginSize + squareSideSize) * 16,
            squareSideSize + 2 * squareMarginSize,
            Bitmap.Config.ARGB_8888)

        Log.d(SIZE_TAG, "the bitmap width is ${gradientBitmap.width}")

        for (i in 0 until gradientBitmap.width)
            for (j in 0 until gradientBitmap.height)
                gradientBitmap.setPixel(i, j, Color.HSVToColor(floatArrayOf(i % 360f, 1f, 1f)))

        val posY = gradientBitmap.height / 2

        for (i in 0..15) {
            ColorPicker[i].layoutParams.width = squareSideSize
            ColorPicker[i].layoutParams.height = squareSideSize
            ColorPicker[i].setOnClickListener {
                mainSquare.background = it.background
                color = colorOfSquares[i]
            }

            val posX = squareMarginSize + squareSideSize / 2 + i * (squareSideSize + 2 * squareMarginSize)
            Log.d(SIZE_TAG, "the position square[i] on X is $posX and on y is $posY")

            colorOfSquares.add(gradientBitmap.getPixel(posX, posY))
            ColorPicker[i].setBackgroundColor(gradientBitmap.getPixel(posX, posY))
            ColorPicker[i].layoutParams = (ColorPicker[i].layoutParams as ViewGroup.MarginLayoutParams)
                .apply { setMargins(squareMarginSize, squareMarginSize, squareMarginSize, squareMarginSize) }
        }

        ColorPicker.background = BitmapDrawable(this.resources, gradientBitmap)
    }
}