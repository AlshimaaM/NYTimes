package com.example.nytimes.ui.article

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nytimes.R
import com.example.nytimes.data.Article
import com.example.nytimes.data.ResultsItem


class ArticleAdapter(var articles: ArrayList<ResultsItem>,
                     private var listener : OnArticleListener
) :
        RecyclerView.Adapter<ArticleAdapter.AtricleViewHolder>() {


    fun updateArticle(newArticle: List<ResultsItem>) {
        articles.clear()
        articles.addAll(newArticle)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = AtricleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
    )
    override fun getItemCount() = articles.size
    override fun onBindViewHolder(holder: AtricleViewHolder, position: Int) {
        holder.bind(articles[position])

    }
    inner class AtricleViewHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val movieImg = view.findViewById<ImageView>(R.id.articleImage)
        private val articleTitle = view.findViewById<TextView>(R.id.articleTitle)
        private val auther = view.findViewById<TextView>(R.id.articleAuthor)
        private val date = view.findViewById<TextView>(R.id.publishDate)

        fun bind(article: ResultsItem) {
            Glide.with(movieImg.context).load(article.multimedia.src).placeholder(R.drawable.ic_launcher_background).into(movieImg)
            articleTitle.text =article.displayTitle
            auther.text =article.byline
            date.text =article.publicationDate

        }
        init {
            view.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
                    listener.onArticleDetailsClick(articles[adapterPosition],adapterPosition)
            }
    }
    interface OnArticleListener
    {
        fun onArticleDetailsClick(item: ResultsItem, position : Int)
    }
}