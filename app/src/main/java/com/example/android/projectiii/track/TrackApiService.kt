package com.example.android.projectiii.track

import com.example.android.projectiii.network.NetworkBuilder
import com.example.android.projectiii.network.TrackResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://ec2-54-161-226-201.compute-1.amazonaws.com:3000/"

interface TrackApiService {
    @GET("tracks")
    suspend fun getTracks(): TrackResponse
}

class TrackApi {
    private lateinit var retrofit: Retrofit

    fun init(): TrackApi {
        val networkBuilder = NetworkBuilder()

        val client = OkHttpClient.Builder()
            .addInterceptor(networkBuilder.getLogger())
            .build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .client(client)
            .build()

        return this
    }

    fun getRetrofitService(): TrackApiService {
        val retrofitService: TrackApiService by lazy {
            retrofit.create(TrackApiService::class.java)
        }

        return retrofitService
    }
}
