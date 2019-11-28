package com.example.android.projectiii.track

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.android.projectiii.challenge.Challenge

@Entity(tableName = "tracks")
@TypeConverters(TrackConverters::class)
data class Track(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    var name: String,
    var challenges: List<Challenge>,
    var currentChallenge: Long = 0L
) {
    fun test(): Challenge {
        Log.d(name ,  currentChallenge.toString() )
        if (currentChallenge.toInt() >= challenges.size){
            return challenges.last()
        }
        return challenges[currentChallenge.toInt()]
    }

    fun completeChallenge() {
        challenges[currentChallenge.toInt()].isDone = true
        if (currentChallenge.toInt() < challenges.size){
            currentChallenge += 1
        }
    }
}