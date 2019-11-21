package com.example.android.projectiii.challenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.projectiii.challenge.ChallengeRepository
import com.example.android.projectiii.challenge.Challenge
import kotlinx.coroutines.launch

class ChallengeViewModel(private val challengeRepository: ChallengeRepository) : ViewModel() {
    private var _challenges = MutableLiveData<List<Challenge>>()
    val challengeList: LiveData<List<Challenge>>
        get() = _challenges

    init {
        viewModelScope.launch {
            initializeChallenges()
        }
    }

    private suspend fun initializeChallenges() {
        _challenges.value = challengeRepository.getAllChallenges()
    }
}