package com.example.android.projectiii.modules

import android.content.Context
import android.net.ConnectivityManager
import com.example.android.projectiii.challenge.ChallengeViewModel
import com.example.android.projectiii.database.EmployeeDao
import com.example.android.projectiii.database.ProjectDatabase
import com.example.android.projectiii.database.TrackDao
import com.example.android.projectiii.employee.EmployeeRepository
import com.example.android.projectiii.employee.EmployeeViewModel
import com.example.android.projectiii.shop.ShopRepository
import com.example.android.projectiii.shop.ShopViewModel
import com.example.android.projectiii.track.TrackApi
import com.example.android.projectiii.track.TrackApiService
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
    single { createTrackRepository(get(), get(), get()) }
    single { createEmployeeRepository(get()) }
    single { createShopRepository() }
    single { createTrackApi() }
    single { createTrackApiService(get()) }
    single { createConnectivityManager(androidContext()) }
    viewModel { TrackViewModel(get()) }
    viewModel { EmployeeViewModel(get()) }
    viewModel { (id: String) -> ChallengeViewModel(get(), id) }
    viewModel { ShopViewModel(get()) }
}

fun createDatabase(context: Context): ProjectDatabase {
    return ProjectDatabase.getInstance(context)
}

fun createEmployeeDao(projectDatabase: ProjectDatabase): EmployeeDao {
    return projectDatabase.employeeDao
}

fun createTrackDao(projectDatabase: ProjectDatabase): TrackDao {
    return projectDatabase.trackDao
}

fun createTrackApi(): TrackApi {
    return TrackApi().init()
}

fun createTrackApiService(trackApi: TrackApi): TrackApiService {
    return trackApi.getRetrofitService()
}

fun createTrackRepository(trackDao: TrackDao, trackApiService: TrackApiService, connectivityManager: ConnectivityManager): TrackRepository {
    return TrackRepository(trackDao, trackApiService, connectivityManager)
}

fun createShopRepository(): ShopRepository {
    return ShopRepository()
}

fun createEmployeeRepository(employeeDao: EmployeeDao): EmployeeRepository {
    return EmployeeRepository(employeeDao)
}

fun createConnectivityManager(context: Context): ConnectivityManager {
    return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}
