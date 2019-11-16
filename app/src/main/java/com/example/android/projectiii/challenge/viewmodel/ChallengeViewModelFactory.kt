package com.example.android.projectiii.challenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.projectiii.challenge.ChallengeRepository
import java.lang.IllegalArgumentException

class ChallengeViewModelFactory(private val challengeRepository: ChallengeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChallengeViewModel::class.java)) {
            return ChallengeViewModel(challengeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}