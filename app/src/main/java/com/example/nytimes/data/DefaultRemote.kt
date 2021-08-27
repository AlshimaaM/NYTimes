package com.example.nytimes.data

import retrofit2.Response

interface DefaultRemote {

     suspend fun fetchArticle(value:String): ArticleResponse?
}