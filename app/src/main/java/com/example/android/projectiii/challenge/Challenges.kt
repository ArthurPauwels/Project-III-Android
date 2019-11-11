package com.example.android.projectiii.challenge

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenges")
data class Challenges (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val deadline: String,
    val isLocked: Boolean,
    val coins: Int,
    val isDone: Boolean,
    val description: String,
    val image: String
)