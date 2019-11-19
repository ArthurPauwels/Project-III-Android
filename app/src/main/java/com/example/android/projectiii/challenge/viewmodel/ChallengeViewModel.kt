package com.example.android.projectiii.challenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.projectiii.challenge.ChallengeRepository
import com.example.android.projectiii.challenge.Challenges
import kotlinx.coroutines.launch

class ChallengeViewModel(private val challengeRepository: ChallengeRepository) : ViewModel() {
    private var _challenges = MutableLiveData<List<Challenges>>()
    val challengesList: LiveData<List<Challenges>>
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