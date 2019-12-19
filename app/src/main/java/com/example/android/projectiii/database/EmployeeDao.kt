package com.example.android.projectiii.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.android.projectiii.employee.Employee

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(employee: Employee)

    @Query("SELECT * FROM employee WHERE id = :employeeId")
    suspend fun getEmployee(employeeId: Long): Employee

    @Update
    suspend fun updateEmployee(employee: Employee)
}
