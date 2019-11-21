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

@Database(entities = [Challenge::class,Track::class, Employee::class], version = 13, exportSchema = false)
abstract class ProjectDatabase : RoomDatabase() {
    abstract val challengeDao: ChallengeDao
    abstract val trackDao : TrackDao
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
                    instance.populateInitialData()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private fun populateInitialData() {
        val challenges = mutableListOf<Challenge>()
        val c1 = Challenge(1, "Run 5km a day", "today is your last day", false, 25, false, "Try to run 5KM a day for the next 5 days, you can do this! So grab your running shoes and hit the track! A 5 kilometer run can burn around 400 Kcal. Remember to run on a soft surface to prevent impact injuries. Grass is a great option if you don't have access to a running track, but make sure the ground is even to prevent falls.", R.drawable.running)
        val c2 = Challenge(2, "Run 6km a day", "for 3 days", true, 30, false, "Run 6KM a day! I believe in you!", R.drawable.running)
        val c3 = Challenge(3, "Run 5km a day", "for 10 days", true, 50, false, "Run 5km a day", R.drawable.running)
        val c4 = Challenge(4, "Run 7km a day", "for 5 days", true, 90, false, "Run 7km a day", R.drawable.running)
        val c5 = Challenge(5, "Eat 1 piece of fruit a day", "1 day left", false, 50, false, "Eat 3 pieces of fruit a day", R.drawable.apple)
        val c6 = Challenge(6, "Eat 2 pieces of fruit a day", "1 day left", false, 50, false, "Eat 3 pieces of fruit a day", R.drawable.apple)
        val c7 = Challenge(7, "Eat 3 pieces of fruit a day", "1 day left", false, 50, false, "Eat 3 pieces of fruit a day", R.drawable.apple)
        challenges.add(c1)
        challenges.add(c2)
        challenges.add(c3)
        challenges.add(c4)
        challenges.add(c5)
        challenges.add(c6)
        challenges.add(c7)

        val tracks = mutableListOf<Track>()
        val cl1 = mutableListOf<Challenge>()
        cl1.add(c1)
        cl1.add(c2)
        val cl2 = mutableListOf<Challenge>()
        cl2.add(c5)
        cl2.add(c6)
        cl2.add(c7)

        val track1 = Track(0,"Get Fit.", cl1, 0)
        val track2 = Track(1,"Eat good.", cl2, 0)
        tracks.add(track1)
        tracks.add(track2)



        val employee = Employee(1, "John", "john@gmail.com", 30)

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                challenges.map { challengeDao.insert(it) }
                tracks.map { trackDao.insert(it) }
                employeeDao.insert(employee)
            }
        }
    }
}