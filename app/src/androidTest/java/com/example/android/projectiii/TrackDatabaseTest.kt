package com.example.android.projectiii

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.projectiii.challenge.Challenge
import com.example.android.projectiii.database.ProjectDatabase
import com.example.android.projectiii.database.TrackDao
import com.example.android.projectiii.track.Track
import java.io.IOException
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TrackDatabaseTest {
    private lateinit var trackDao: TrackDao
    private lateinit var db: ProjectDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, ProjectDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        trackDao = db.trackDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun insertAndGetTrack() {
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

        runBlocking {
            var allTracks = trackDao.getTracks()
            assertTrue(allTracks.isEmpty())
            trackDao.insert(track)
            allTracks = trackDao.getTracks()
            assertTrue(allTracks.size == 1)
        }
    }
}
