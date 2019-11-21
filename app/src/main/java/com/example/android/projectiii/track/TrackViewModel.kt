package com.example.android.projectiii.track

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TrackViewModel(private val trackRepository: TrackRepository) : ViewModel() {
    private var _tracks = MutableLiveData<List<Track>>()
    val trackList: LiveData<List<Track>>
        get() = _tracks

    init {
        viewModelScope.launch {
            initializeTracks()
        }
    }

    private suspend fun initializeTracks() {
        _tracks.value = trackRepository.getAllTracks()
    }
}