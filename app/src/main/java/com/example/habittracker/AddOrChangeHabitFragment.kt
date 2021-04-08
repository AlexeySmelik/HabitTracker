package com.example.habittracker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.add_or_change_habit_fragment.*

interface AddOrChangeHabitCallback{
    fun onSaveHabit(habit: Habit, fragment: AddOrChangeHabitFragment)
}

class AddOrChangeHabitFragment: Fragment() {
    companion object {
        const val COLOR: String = "color"
        private const val ARG_INFO = "arg_info"
        private const val ARG_NAME = "arg_name"
    }

    var name: String = ""
    var callback: AddOrChangeHabitCallback? = null

    private var color: Int = Color.BLACK
    private var colorOfSquares: MutableList<Int> = mutableListOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as AddOrChangeHabitCallback
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        = inflater.inflate(R.layout.add_or_change_habit_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_NAME, "")
            val habit = it.getParcelable<Habit>(ARG_INFO)
            if (habit != null) {
                restoreParams(habit)
                color = habit.color
            }
        }

        if (savedInstanceState != null) {
            color = savedInstanceState.getInt(COLOR)
            mainSquare.setBackgroundColor(color)
        }

        submit_habit.setOnClickListener { callback?.onSaveHabit(makeHabit(), this) }

        makeColorPicker()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COLOR, color)
    }

    private fun makeHabit(): Habit = Habit(
            title = title_edit.text.toString(),
            description = description_edit.text.toString(),
            priority = priority_spinner.selectedItem.toString(),
            type = if (good_habit.isChecked) good_habit.text.toString() else bad_habit.text.toString(),
            period = if (period_edit.text.toString() != "") period_edit.text.toString() else "1",
            quantity = if (quantity_edit.text.toString() != "") quantity_edit.text.toString() else "1",
            color)

    private fun restoreParams(habit: Habit) {
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

    private fun makeColorPicker() {
        val width = activity!!.windowManager.defaultDisplay.width
        val squareSideSize = width / 8
        val squareMarginSize = width / 12

        val gradientBitmap: Bitmap = Bitmap.createBitmap(
            (2 * squareMarginSize + squareSideSize) * 16,
            squareSideSize + 2 * squareMarginSize,
            Bitmap.Config.ARGB_8888)


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

            colorOfSquares.add(gradientBitmap.getPixel(posX, posY))
            ColorPicker[i].setBackgroundColor(gradientBitmap.getPixel(posX, posY))
            ColorPicker[i].layoutParams = (ColorPicker[i].layoutParams as ViewGroup.MarginLayoutParams)
                .apply { setMargins(squareMarginSize, squareMarginSize, squareMarginSize, squareMarginSize) }
        }

        ColorPicker.background = BitmapDrawable(this.resources, gradientBitmap)
    }
}