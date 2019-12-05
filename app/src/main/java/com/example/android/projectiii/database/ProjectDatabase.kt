package com.example.android.projectiii.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.projectiii.R
import com.example.android.projectiii.track.Track
import com.example.android.projectiii.challenge.Challenge
import com.example.android.projectiii.employee.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(
    entities = [Challenge::class, Track::class, Employee::class],
    version = 15,
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

                context.deleteDatabase("challenge_database")

                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProjectDatabase::class.java,
                        "challenge_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    instance.populateInitialData()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private fun populateInitialData() {
        val c1 = Challenge(
            1,
            "Run 5km a day",
            "5 days",
            false,
            25,
            false,
            "Run 5km a day",
            R.drawable.running
        )
        val c2 = Challenge(
            2,
            "Run 6km a day",
            "5 days",
            false,
            30,
            false,
            "Run 6km a day",
            R.drawable.running
        )
        val c3 = Challenge(
            3,
            "Run 7km a day",
            "5 days",
            false,
            50,
            false,
            "Run 7km a day",
            R.drawable.running
        )
        val c4 = Challenge(
            4,
            "Run 8km a day",
            "5 days",
            false,
            90,
            false,
            "Run 8km a day",
            R.drawable.running
        )
        val c5 = Challenge(
            5,
            "Eat 1 piece of fruit a day",
            "1 day left",
            false,
            50,
            true,
            "Eat 3 pieces of fruit a day",
            R.drawable.apple
        )
        val c6 = Challenge(
            6,
            "Eat 2 pieces of fruit a day",
            "1 day left",
            false,
            50,
            false,
            "Eat 3 pieces of fruit a day",
            R.drawable.apple
        )
        val c7 = Challenge(
            7,
            "Eat 3 pieces of fruit a day",
            "1 day left",
            false,
            50,
            false,
            "Eat 3 pieces of fruit a day",
            R.drawable.apple
        )

        val tracks = mutableListOf<Track>()
        val cl1 = mutableListOf<Challenge>()
        cl1.add(c1)
        cl1.add(c2)
        cl1.add(c3)
        cl1.add(c4)
        val cl2 = mutableListOf<Challenge>()
        cl2.add(c5)
        cl2.add(c6)
        cl2.add(c7)

        val track1 = Track(1, "Get Fit.", cl1, 0)
        val track2 = Track(2, "Eat good.", cl2, 1)
        tracks.add(track1)
        tracks.add(track2)


        val employee = Employee(1, "John", "john@gmail.com", 30)

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                tracks.map { trackDao.insert(it) }
                employeeDao.insert(employee)
            }
        }
    }
}