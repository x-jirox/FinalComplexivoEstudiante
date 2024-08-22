package com.example.hotelcomplexivo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
//    private const val BASE_URL = "http://192.168.127.43:8080"
       private const val BASE_URL = "http://complexivoexam.us-east-1.elasticbeanstalk.com/"


    val retrofit: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

