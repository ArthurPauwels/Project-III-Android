package com.example.android.projectiii.network

import okhttp3.logging.HttpLoggingInterceptor

class NetworkBuilder {
    fun getLogger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }
}