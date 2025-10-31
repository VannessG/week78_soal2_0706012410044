package com.example.week78_0706012410044_soal2.data.container

import com.example.week78_0706012410044_soal2.data.service.ArtistService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ArtistContainer {
    private const val BASE_URL = "https://www.theaudiodb.com/api/v1/json/123/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val artistService: ArtistService by lazy {
        retrofit.create(ArtistService::class.java)
    }
}