package com.example.android.projectiii

import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.android.projectiii.challenge.Challenge
import com.example.android.projectiii.database.TrackDao
import com.example.android.projectiii.network.TrackResponse
import com.example.android.projectiii.track.Track
import com.example.android.projectiii.track.TrackApiService
import com.example.android.projectiii.track.TrackRepository
import io.mockk.Called
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class TrackRepositoryTest {
    private lateinit var repository: TrackRepository
    private val trackDao = mockk<TrackDao>()
    private val trackApiService = mockk<TrackApiService>()
    private val connectivityManager = mockk<ConnectivityManager>()
    private val networkInfo = mockk<NetworkInfo>()

    @Before
    fun setUp() {
        repository = TrackRepository(trackDao, trackApiService, connectivityManager)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun trackRepository_getAllTracks_noInternet_onlyCallsDatabase() {
        every { networkInfo.isConnected } returns false
        every { connectivityManager.activeNetworkInfo } returns networkInfo
        coEvery { trackDao.getTracks() } returns listOf()

        runBlocking {
            repository.getAllTracks()

            coVerify { trackDao.getTracks() }
            coVerify { trackApiService.getTracks() wasNot Called }
        }
    }

    @Test
    fun trackRepository_getAllTracks_internetAvailable_savesNetworkCallInDatabase_IfChallengeList_isNotEmpty() {
        every { networkInfo.isConnected } returns true
        every { connectivityManager.activeNetworkInfo } returns networkInfo

        val challenge = Challenge(
            _id = "anid",
            name = "name",
            description = "",
            reward = 4,
            deadline = "",
            parentId = "anid",
            image = 2
        )
        val track = Track(
            _id = "anid",
            name = "name",
            expert = "Expert",
            challenges = listOf(challenge)
        )
        val trackResponse = TrackResponse(docs = listOf(track))
        coEvery { trackApiService.getTracks() } returns trackResponse
        coEvery { trackDao.insert(any()) } returns Unit
        coEvery { trackDao.getTracks() } returns listOf()

        runBlocking {
            repository.getAllTracks()

            coVerify { trackDao.insert(track) }
        }
    }

    @Test
    fun trackRepository_getAllTracks_internetAvailable_notSavesNetworkCallInDatabase_IfChallengeList_isEmpty() {
        every { networkInfo.isConnected } returns true
        every { connectivityManager.activeNetworkInfo } returns networkInfo

        val track = Track(
            _id = "anid",
            name = "name",
            expert = "Expert",
            challenges = listOf()
        )
        val trackResponse = TrackResponse(docs = listOf(track))
        coEvery { trackApiService.getTracks() } returns trackResponse
        coEvery { trackDao.insert(any()) } returns Unit
        coEvery { trackDao.getTracks() } returns listOf()

        runBlocking {
            repository.getAllTracks()

            coVerify { trackDao.insert(track) wasNot Called }
        }
    }
}
