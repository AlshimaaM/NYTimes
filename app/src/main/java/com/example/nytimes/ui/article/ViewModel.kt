package com.example.nytimes.ui

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.*
import com.example.nytimes.data.ArticleResponse
import com.example.nytimes.data.ResultsItem
import com.example.nytimes.data.repo.DefaultRepo
import com.example.nytimes.util.Network
import kotlinx.coroutines.launch


class ArticleViewModel(application: Application, var apiRepository : DefaultRepo) : AndroidViewModel(application){
    var allArticle = MutableLiveData<ArticleResponse>()

    fun getAllArticle() {
        viewModelScope.launch {
            apiRepository.fetchArticle().let {
                allArticle.postValue(it )
            }
        }
    }

    fun openWebPage(application: Application,item : Int) {
        val webPage: Uri = Uri.parse(allArticle.value!!.results[item].link.url)
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if (intent.resolveActivity(application.packageManager) != null) {
            application.startActivity(intent)
        }
    }


}
@Suppress("UNCHECKED_CAST")
class ArticleViewModelFactory(val application: Application,val repo: DefaultRepo): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(application, repo) as T

        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}