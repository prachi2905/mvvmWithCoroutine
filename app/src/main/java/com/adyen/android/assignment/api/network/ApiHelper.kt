package com.adyen.android.assignment.api.network

import com.adyen.android.assignment.api.model.AstronomyPicture
import retrofit2.Response

interface ApiHelper {
    suspend fun getAstronomyPictures(): Response<List<AstronomyPicture>>
}