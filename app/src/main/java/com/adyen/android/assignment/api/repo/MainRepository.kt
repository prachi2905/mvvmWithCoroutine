package com.adyen.android.assignment.api.repo

import com.adyen.android.assignment.api.network.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getAstronomyImageApiCall() = apiHelper.getAstronomyPictures()
}