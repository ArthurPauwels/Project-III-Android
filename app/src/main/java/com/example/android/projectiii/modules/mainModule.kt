package com.example.android.projectiii.modules

import android.content.Context
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
    single { createEmployeeDao(get()) }
    single { createTrackDao(get()) }
    single { createTrackRepository(get()) }
    single { createEmployeeRepository(get()) }
    viewModel { TrackViewModel(get()) }
    viewModel { EmployeeViewModel(get()) }
}

fun createDatabase (context: Context) : ProjectDatabase {
    return ProjectDatabase.getInstance(context)
}

fun createEmployeeDao (projectDatabase: ProjectDatabase) : EmployeeDao {
    return projectDatabase.employeeDao
}

fun createTrackDao (projectDatabase: ProjectDatabase) : TrackDao {
    return projectDatabase.trackDao
}

fun createTrackRepository (trackDao: TrackDao) : TrackRepository {
    return TrackRepository(trackDao)
}

fun createEmployeeRepository (employeeDao: EmployeeDao) : EmployeeRepository {
    return EmployeeRepository(employeeDao)
}