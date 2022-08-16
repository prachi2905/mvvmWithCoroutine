package com.adyen.android.assignment.api.network

import com.adyen.android.assignment.BuildConfig
import com.adyen.android.assignment.api.model.AstronomyPicture
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("planetary/apod?count=20&api_key=${BuildConfig.API_KEY}")
    suspend fun getPictures(): Response<List<AstronomyPicture>>
}