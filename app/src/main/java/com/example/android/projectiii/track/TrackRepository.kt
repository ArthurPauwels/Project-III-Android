package com.example.android.projectiii.track

import android.net.ConnectivityManager
import com.example.android.projectiii.R
import com.example.android.projectiii.challenge.Challenge
import com.example.android.projectiii.database.TrackDao

class TrackRepository(
    private val trackDao: TrackDao,
    private val trackApiService: TrackApiService,
    private val connectivityManager: ConnectivityManager
) {
    suspend fun getAllTracks(): List<Track> {
        if (isConnected()) {
            val result = trackApiService.getTracks()

            var trackList = mutableListOf<Track>()

            for (track in result.docs) {

                var challengeList = mutableListOf<Challenge>()

                for (challenge in track.challenges) {
                    val newChallenge = Challenge(
                        _id = challenge._id,
                        reward = challenge.reward,
                        name = challenge.name,
                        description = challenge.description,
                        deadline = "In 3 days",
                        parentId = track._id,
                        image = R.drawable.apple
                    )

                    challengeList.add(newChallenge)
                }

                track.challenges = challengeList

                // Insert only if we have one challenges to display
                if (track.challenges.size > 0) {
                    trackDao.insert(track)
                    trackList.add(track)
                }
            }

            return trackList
        }

        return getUndoneChallenge()
    }

    suspend fun getUndoneChallenge(): List<Track> {
        var newTrackList = mutableListOf<Track>()
        var dbList = trackDao.getTracks()

        for (track in dbList) {
            if (!track.isComplete()) {
                newTrackList.add(track)
            }
        }

        return newTrackList
    }

    suspend fun getChallengesForTrack(id: String): List<Challenge> {
        val trackList = trackDao.getTracks()

        for (track in trackList) {
            if (track._id == id) {
                return track.challenges
            }
        }

        return listOf()
    }

    suspend fun updateTracks(tracks: List<Track>) {
        trackDao.updateTracks(tracks)
    }

    private fun isConnected(): Boolean {
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }
}
