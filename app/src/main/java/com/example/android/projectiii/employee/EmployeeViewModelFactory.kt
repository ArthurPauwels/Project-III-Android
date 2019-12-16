package com.example.android.projectiii.employee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class EmployeeViewModelFactory(private val employeeRepository: EmployeeRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
            return EmployeeViewModel(employeeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
