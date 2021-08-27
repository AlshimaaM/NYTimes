package com.example.nytimes.ui.article

import android.R.attr.fragment
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytimes.R
import com.example.nytimes.data.Article
import com.example.nytimes.data.DefaultRemote
import com.example.nytimes.data.RemoteDataSource
import com.example.nytimes.data.ResultsItem
import com.example.nytimes.data.repo.ApiRepository
import com.example.nytimes.data.repo.DefaultRepo
import com.example.nytimes.databinding.ArticleFragmentBinding
import com.example.nytimes.ui.ArticleViewModel
import com.example.nytimes.ui.ArticleViewModelFactory
import com.example.nytimes.ui.details.DetailsFragment
import com.example.nytimes.ui.settings.SettingsFragment
import com.example.nytimes.util.Network


class ArticlesFragment: Fragment(), ArticleAdapter.OnArticleListener {
    private lateinit var binding: ArticleFragmentBinding
    private lateinit var viewModel: ArticleViewModel
    var  articleAdapter = ArticleAdapter(arrayListOf(), this)
    private lateinit var articleList:ArrayList<ResultsItem>
    lateinit var repository: DefaultRepo
    lateinit var remote: DefaultRemote


        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

        binding =  DataBindingUtil.inflate(inflater, R.layout.article_fragment, container, false)
        remote= RemoteDataSource()
        repository= ApiRepository(
            requireActivity().application, PreferenceManager.getDefaultSharedPreferences(
                requireContext()
            ), remote
        )

        val factory = ArticleViewModelFactory(requireActivity().application, repository)
        viewModel = ViewModelProviders.of(this, factory).get(ArticleViewModel::class.java)

        initUI()
        loadArticles()
            openSettings()

        return binding.root
    }
    private fun loadArticles() {
        if (Network.isOnline(requireContext())) {
            viewModel.getAllArticle()
        } else {
            binding.internetImg.visibility =View.VISIBLE
        }
        viewModel.allArticle?.observe(requireActivity(), Observer {
            articleList = it.results as ArrayList<ResultsItem>
            articleAdapter.updateArticle(articleList)
        })
    }

    private fun initUI() {
        binding.articlesRecycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = articleAdapter
        }
    }

    private fun openSettings() {
        binding.openSetting.setOnClickListener({
            requireActivity()!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, SettingsFragment(), "details")
                .addToBackStack(null)
                .commit()
        })
    }


    override fun onArticleDetailsClick(item: ResultsItem,position : Int) {
            val frag = DetailsFragment()
            val bundle = Bundle()
            bundle.putInt("item",position)
            frag.setArguments(bundle)
            requireActivity()!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, frag, "details")
                .addToBackStack(null)
                .commit()

    }

}