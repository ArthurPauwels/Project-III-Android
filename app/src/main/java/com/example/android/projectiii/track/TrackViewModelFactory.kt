package com.example.android.projectiii.track

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class TrackViewModelFactory(private val trackRepository: TrackRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrackViewModel::class.java)) {
            return TrackViewModel(trackRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
