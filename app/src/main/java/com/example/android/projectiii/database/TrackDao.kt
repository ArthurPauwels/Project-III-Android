package com.example.android.projectiii.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.projectiii.track.Track

@Dao
interface TrackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(track: Track)

    @Query("SELECT * FROM tracks")
    suspend fun getTracks(): List<Track>
}