package com.example.android.projectiii.challenge

import com.example.android.projectiii.database.ChallengeDao

class ChallengeRepository (private val challengeDao: ChallengeDao) {
    suspend fun getAllChallenges(): List<Challenges> {
        return challengeDao.getChallenges()
    }
}