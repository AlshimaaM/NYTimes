package com.example.nytimes.data.repo

import com.example.nytimes.data.Article
import com.example.nytimes.data.ArticleResponse
import com.example.nytimes.data.ResultsItem
import retrofit2.Response

interface DefaultRepo {
    suspend fun fetchArticle(): ArticleResponse?

}