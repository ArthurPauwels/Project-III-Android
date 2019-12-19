package com.example.android.projectiii.employee

import com.example.android.projectiii.database.EmployeeDao

class EmployeeRepository(private val employeeDao: EmployeeDao) {
    suspend fun getEmployee(employeeId: Long): Employee {
        return employeeDao.getEmployee(employeeId)
    }

    suspend fun updateEmployee(employee: Employee) {
        employeeDao.updateEmployee(employee)
    }
}
