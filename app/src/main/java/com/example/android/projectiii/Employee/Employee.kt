package com.example.android.projectiii.Employee

import android.util.Log

class Employee (val name: String, val email: String, val password: String, var totalCoins: Int){
    fun addCoins(nrOfCoins: Int){
        totalCoins += nrOfCoins
        Log.d("Check", totalCoins.toString())
    }
}
