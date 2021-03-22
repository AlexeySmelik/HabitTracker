package com.example.habittracker

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Habit(
    var title: String,
    var description: String,
    var priority: String,
    var type: String,
    var period: String,
    var quantity: String,
    var color: Int
    ) : Parcelable