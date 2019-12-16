package com.example.android.projectiii.modules

import android.content.Context
import com.example.android.projectiii.challenge.ChallengeRepository
import com.example.android.projectiii.challenge.viewmodel.ChallengeViewModel
import com.example.android.projectiii.database.ChallengeDao
import com.example.android.projectiii.database.EmployeeDao
import com.example.android.projectiii.database.ProjectDatabase
import com.example.android.projectiii.database.TrackDao
import com.example.android.projectiii.employee.EmployeeRepository
import com.example.android.projectiii.employee.EmployeeViewModel
import com.example.android.projectiii.track.TrackRepository
import com.example.android.projectiii.track.TrackViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    // Tracks
    single { createDatabase(androidContext()) }
    single { createChallengeDao(get()) }
    single { createEmployeeDao(get()) }
    single { createTrackDao(get()) }
    single { createChallengeRepository(get()) }
    single { createTrackRepository(get()) }
    single { createEmployeeRepository(get()) }
    viewModel { TrackViewModel(get()) }
    viewModel { ChallengeViewModel(get()) }
    viewModel { EmployeeViewModel(get()) }
}

fun createDatabase (context: Context) : ProjectDatabase {
    return ProjectDatabase.getInstance(context)
}

fun createChallengeDao (projectDatabase: ProjectDatabase) : ChallengeDao {
    return projectDatabase.challengeDao
}

fun createEmployeeDao (projectDatabase: ProjectDatabase) : EmployeeDao {
    return projectDatabase.employeeDao
}

fun createTrackDao (projectDatabase: ProjectDatabase) : TrackDao {
    return projectDatabase.trackDao
}

fun createChallengeRepository (challengeDao: ChallengeDao) : ChallengeRepository {
    return ChallengeRepository(challengeDao)
}

fun createTrackRepository (trackDao: TrackDao) : TrackRepository {
    return TrackRepository(trackDao)
}

fun createEmployeeRepository (employeeDao: EmployeeDao) : EmployeeRepository {
    return EmployeeRepository(employeeDao)
}