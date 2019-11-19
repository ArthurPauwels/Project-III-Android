package com.example.android.projectiii.database

import androidx.room.*
import com.example.android.projectiii.employee.Employee

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Query("SELECT * FROM employee WHERE id = :employeeId")
    suspend fun getEmployee(employeeId: Long): Employee
}