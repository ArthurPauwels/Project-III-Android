package com.example.android.projectiii.employee

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var name: String,
    var email: String,
    var totalCoins: Int
)