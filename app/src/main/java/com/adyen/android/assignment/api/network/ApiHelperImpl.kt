package com.adyen.android.assignment.api.network

import com.adyen.android.assignment.api.model.AstronomyPicture
import retrofit2.Response


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getAstronomyPictures(): Response<List<AstronomyPicture>> =
        apiService.getPictures()
}