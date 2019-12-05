package com.example.android.projectiii.employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EmployeeViewModel (private val employeeRepository: EmployeeRepository) : ViewModel() {
    private var _employee = MutableLiveData<Employee>()
    var isUpdated = MutableLiveData<Boolean>(false)

    val employee: LiveData<Employee>
        get() = _employee

    init {
        viewModelScope.launch {
            _employee.value = employeeRepository.getEmployee(1)
        }
    }

    fun addCoins(coins: Int) {
        _employee.value?.let {
            it.totalCoins += coins
            isUpdated.value = true
            viewModelScope.launch {
                employeeRepository.updateEmployee(it)
            }
        }
    }
}