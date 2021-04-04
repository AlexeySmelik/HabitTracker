package com.example.habittracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null){
            val transaction = supportFragmentManager.beginTransaction()
            val mainFragment = HabitListFragment.newInstance("listOfHabits")
            transaction
                .add(R.id.fragment_container, mainFragment, "MAIN")
                .commit()
        }
    }
}