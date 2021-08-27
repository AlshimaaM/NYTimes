package com.example.nytimes.ui.details

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nytimes.data.ResultsItem
import com.example.nytimes.data.repo.DefaultRepo
import com.example.nytimes.ui.ArticleViewModel

class DetailsViewModel(application: Application, var apiRepository : DefaultRepo) : AndroidViewModel(application){

}


@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(val application: Application,val repo: DefaultRepo): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(application, repo) as T

        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}