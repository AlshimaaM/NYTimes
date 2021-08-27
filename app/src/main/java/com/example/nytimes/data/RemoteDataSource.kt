package com.example.nytimes.data

import android.util.Log
import com.example.nytimes.data.retrofit.RetrofitInstance

class RemoteDataSource :DefaultRemote {

    override suspend fun fetchArticle(value:String): ArticleResponse? {

        val response = RetrofitInstance.movieApi.fetchMovies(value)
        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.i("data", "response" + it)
                    return it
                }
            } else {
                Log.i("data", "response failuer" + response.errorBody().toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("data", " error?" + e.printStackTrace())


        }
        return null

    }



}