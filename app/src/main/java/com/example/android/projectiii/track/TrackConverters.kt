package com.example.android.projectiii.track


import androidx.room.TypeConverter
import com.example.android.projectiii.challenge.Challenge
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class TrackConverters {
    private val gson = Gson()

    @TypeConverter
    fun listToJson(value: List<Challenge>?): String = gson.toJson(value)

    @TypeConverter
    fun fromString(value: String): List<Challenge> =
        gson.fromJson(value, object : TypeToken<List<Challenge>>() {}.getType())
}