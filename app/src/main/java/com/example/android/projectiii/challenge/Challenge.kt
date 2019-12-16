package com.example.android.projectiii.challenge

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.android.projectiii.track.Track

@Entity(tableName = "challenges")
@ForeignKey(entity = Track::class,
    parentColumns = ["trackId"],
    childColumns = ["parentId"],
    onDelete = ForeignKey.NO_ACTION)
data class Challenge(
    @PrimaryKey
    @ColumnInfo(name="challengeId")
    val _id: String,
    val reward: Int,
    @ColumnInfo(name="challengeName")
    val name: String,
    val description: String,

    val deadline: String?,
    val parentId: String?,
    val image: Int?,
    var isDone: Boolean = false,
    var isOpen: Boolean = false
) {
    fun complete() {
        isDone = true
    }
}
