package com.example.hotelcomplexivo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
//    private const val BASE_URL = "http://complexivoexam.us-east-1.elasticbeanstalk.com/"

    private const val BASE_URL = "http://complexivoexam.us-east-1.elasticbeanstalk.com/"

    val instance: Retrofit by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
