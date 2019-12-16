package com.example.android.projectiii

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.projectiii.track.TracksListFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BottomNavigationTest {
    @Test
    fun testNavigationToLockedChallenges() {
        val mockNavController = mock(NavController::class.java)

        val currentScenario = launchFragmentInContainer<TracksListFragment>()

        currentScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }
    }
}
