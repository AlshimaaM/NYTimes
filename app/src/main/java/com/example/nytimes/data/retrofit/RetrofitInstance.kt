package com.example.nytimes.data.retrofit

import com.example.nytimes.data.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val movieApi: MovieApi = getRetrofit()
        .create(MovieApi::class.java)


}