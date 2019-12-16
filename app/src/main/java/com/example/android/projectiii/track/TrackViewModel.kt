package com.example.android.projectiii.track

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TrackViewModel(private val trackRepository: TrackRepository) : ViewModel() {
    private var _tracks = MutableLiveData<List<Track>>()
    var isUpdated = MutableLiveData<Boolean>(false)

    val trackList: LiveData<List<Track>>
        get() = _tracks

    init {
        viewModelScope.launch {
            _tracks.value = trackRepository.getAllTracks()
        }
    }

    fun completeChallenge(trackId: Long) {
        _tracks.value?.let {
            it[it.indexOfFirst { t -> t.id == trackId }].completeChallenge()
            isUpdated.value = true
            viewModelScope.launch {
                trackRepository.updateTracks(it)
            }
        }
    }
}