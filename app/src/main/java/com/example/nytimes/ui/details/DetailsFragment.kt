package com.example.nytimes.ui.details

import android.app.Application
import android.content.Intent
import android.net.Uri
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
import com.bumptech.glide.Glide
import com.example.nytimes.R
import com.example.nytimes.data.Article
import com.example.nytimes.data.DefaultRemote
import com.example.nytimes.data.RemoteDataSource
import com.example.nytimes.data.ResultsItem
import com.example.nytimes.data.repo.ApiRepository
import com.example.nytimes.data.repo.DefaultRepo
import com.example.nytimes.databinding.DetailsFragmentBinding
import com.example.nytimes.ui.ArticleViewModel
import com.example.nytimes.ui.ArticleViewModelFactory
import com.example.nytimes.util.Network
import kotlin.properties.Delegates

class DetailsFragment: Fragment() {
    private lateinit var binding: DetailsFragmentBinding
    private var item : Int = -1
    private lateinit var viewModel: ArticleViewModel
    private lateinit var img : String
    lateinit var repository: DefaultRepo
    lateinit var remote: DefaultRemote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getInt("item")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.details_fragment, container, false)
        remote= RemoteDataSource()
        repository= ApiRepository(requireActivity().application, PreferenceManager.getDefaultSharedPreferences(requireContext()), remote)

        val factory = ArticleViewModelFactory(requireActivity().application, repository)
        viewModel = ViewModelProviders.of(this, factory).get(ArticleViewModel::class.java)
        binding.openWeb.setOnClickListener({
            viewModel.openWebPage(requireActivity().application,item)
        })
       loadArticleDetails()
        return binding.root
    }

    private fun loadArticleDetails() {
        if (Network.isOnline(requireContext())) {
            viewModel.getAllArticle()
        } else {
            binding.internetImg.visibility =View.VISIBLE
        }
        viewModel.allArticle?.observe(requireActivity(), Observer {
                binding.articleSubject.text = it.results[item].headline
                binding.title.text = it.results[item].displayTitle
                binding.caption.text = it.results[item].summaryShort
                binding.byLine.text = it.results[item].byline
                binding.published.text = "published at "+ it.results[item].publicationDate+"\n"+"updated at " + it.results[item].dateUpdated
                img = it.results[item].multimedia.src
                context?.let {
                    Glide.with(it).load(img).into(binding.articleImg)

                }
            binding.loading.visibility =View.GONE

        })
    }




}