package com.example.android.projectiii.track

import com.example.android.projectiii.database.TrackDao

class TrackRepository(private val trackDao: TrackDao) {
    suspend fun getAllTracks(): List<Track> {
        return trackDao.getTracks()
    }
}