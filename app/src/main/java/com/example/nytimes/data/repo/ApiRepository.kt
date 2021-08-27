package com.example.nytimes.data.repo

import android.app.Application
import android.content.SharedPreferences
import com.example.nytimes.data.Article
import com.example.nytimes.data.ArticleResponse
import com.example.nytimes.data.DefaultRemote
import com.example.nytimes.data.ResultsItem
import retrofit2.Response

class ApiRepository (application: Application, private val sharedPreferences: SharedPreferences, var remote: DefaultRemote) : DefaultRepo {

    override suspend fun fetchArticle(): ArticleResponse? {
        val value = sharedPreferences.getString("type", "all.json")
        val response = remote.fetchArticle(value= value!!)
        return response

    }
}