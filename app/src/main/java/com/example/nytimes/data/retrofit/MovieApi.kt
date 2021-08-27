package com.example.nytimes.data.retrofit

import com.example.nytimes.data.Constant.API_KEY
import com.example.nytimes.data.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("{all}")
    suspend fun fetchMovies(
        @Path("all") value: String,
        @Query("api-key") key: String = API_KEY,
    ): Response<ArticleResponse>
}