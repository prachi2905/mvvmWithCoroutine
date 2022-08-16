package com.adyen.android.assignment.api.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate


class DayAdapter {
    @ToJson
    fun toJson(date: LocalDate): String = date.toString()

    /**
     * Maps the [AstronomyPicture.date] json string to a [LocalDate]
     */
    @FromJson
    fun fromJson(date: String): LocalDate = LocalDate.parse(date)
}