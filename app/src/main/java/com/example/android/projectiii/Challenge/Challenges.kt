package com.example.android.projectiii.Challenge

data class Challenges (
    val id: String,
    val title: String,
    val deadline: String,
    val isLocked: Boolean,
    val coins: Int,
    val isDone: Boolean,
    val description: String
)