package com.example.android.projectiii.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.projectiii.R
import com.example.android.projectiii.challenge.Challenge
import com.example.android.projectiii.employee.Employee
import com.example.android.projectiii.track.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

@Database(
    entities = [Track::class, Employee::class],
    version = 20,
    exportSchema = false
)
abstract class ProjectDatabase : RoomDatabase() {
    abstract val trackDao: TrackDao
    abstract val employeeDao: EmployeeDao

    companion object {
        @Volatile
        private var INSTANCE: ProjectDatabase? = null

        fun getInstance(context: Context): ProjectDatabase {
            synchronized(this) {

                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProjectDatabase::class.java,
                        "challenge_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    instance.populateEmployee()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private fun populateEmployee() {
        val employee = Employee(1, "John", "john@gmail.com", 30)

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    employeeDao.getEmployee(1)
                } catch (e: Exception) {
                    employeeDao.insert(employee)
                }
            }
        }
    }
}
