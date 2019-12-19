package com.example.android.projectiii.track

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.android.projectiii.challenge.Challenge

@Entity(tableName = "tracks")
@TypeConverters(TrackConverters::class)
data class Track(
    @PrimaryKey
    @ColumnInfo(name = "trackId")
    var _id: String,
    var name: String,
    var expert: String,
    var challenges: List<Challenge>,
    var currentChallenge: String = ""
) {
    fun countUndoneChallenge(): Int {
        var undone = 0

        for (challenge in challenges) {
            if (!challenge.isDone) {
                undone += 1
            }
        }

        return undone
    }

    fun getFirstUndone(): Challenge? {
        for (challenge in challenges) {
            if (!challenge.isDone) {
                return challenge
            }
        }
        return null
    }

    fun completeCurrentChallenge() {
        for (challenge in challenges) {
            if (!challenge.isDone) {
                challenge.isDone = true
                return
            }
        }
    }

    fun isComplete(): Boolean {
        val nbUndone = countUndoneChallenge()

        if (nbUndone == 0) {
            return true
        }

        return false
    }
}
