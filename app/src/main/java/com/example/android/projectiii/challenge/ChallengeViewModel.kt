package com.example.android.projectiii.challenge

import com.example.android.projectiii.track.Track
import com.example.android.projectiii.track.TrackRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ChallengeViewModel(private val trackRepository: TrackRepository, private val idTrack: String) : ViewModel() {
    private var _challenges = MutableLiveData<List<Challenge>>()
    var isUpdated = MutableLiveData<Boolean>(false)

    val challengeList: LiveData<List<Challenge>>
        get() = _challenges

    init {
        viewModelScope.launch {
            _challenges.value = trackRepository.getChallengesForTrack(idTrack)
        }
    }
}
