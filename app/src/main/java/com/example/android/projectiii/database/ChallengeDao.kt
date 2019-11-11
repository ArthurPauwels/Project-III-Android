package com.example.android.projectiii.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.projectiii.challenge.Challenges

@Dao
interface ChallengeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(challenge: Challenges)

    @Query("SELECT * FROM challenges")
    suspend fun getChallenges(): List<Challenges>
}