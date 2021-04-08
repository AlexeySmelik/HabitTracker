import com.example.habittracker.Habit

interface AddOrChangeHabitCallback{
    fun onSaveHabit(habit: Habit, fragmentName: String)
}