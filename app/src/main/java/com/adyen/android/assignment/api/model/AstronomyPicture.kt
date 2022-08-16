package com.adyen.android.assignment.api.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate


@Parcelize
data class AstronomyPicture(
    @Json(name = "service_version") val serviceVersion: String,
    val title: String,
    val explanation: String,
    val date: LocalDate,
    @Json(name = "media_type") val mediaType: String,
    @Json(name = "hdurl") val hdUrl: String?,
    val url: String
) : Parcelable {
    val coverImageUrlBig: String
        get() = if (!url.isBlank()) url.plus("l") else ""
}