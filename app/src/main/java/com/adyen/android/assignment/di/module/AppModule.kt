package com.android.post.di.module

import com.adyen.android.assignment.BuildConfig
import com.adyen.android.assignment.api.model.DayAdapter
import com.adyen.android.assignment.api.network.ApiHelper
import com.adyen.android.assignment.api.network.ApiHelperImpl
import com.adyen.android.assignment.api.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val AppModule = module {

    single { createRetrofit(get(), BuildConfig.NASA_BASE_URL) }

    single { MoshiConverterFactory.create() }

    single { Moshi.Builder().build() }

    single { provideApiService(get()) }

    single { createOkHttpClient() }


    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
}

private val moshi: Moshi = Moshi
    .Builder()
    .add(DayAdapter())
    .add(KotlinJsonAdapterFactory())
    .build()

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)