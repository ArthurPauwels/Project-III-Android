package com.example.android.projectiii.track

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
        return challenges[currentChallenge.toInt()]
    }
}