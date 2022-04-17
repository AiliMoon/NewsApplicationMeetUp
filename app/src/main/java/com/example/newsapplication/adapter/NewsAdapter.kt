package com.example.newsapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.news.ArticleModel
import com.example.newsapplication.R

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    private val list = mutableListOf<ArticleModel>()

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_item_layout, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewsAdapter.ArticleViewHolder, position: Int) {
        val article = list[position]

        holder.itemView.apply {
            val newsImage = findViewById<ImageView>(R.id.news_image)
            val title = findViewById<TextView>(R.id.title)
            val description = findViewById<TextView>(R.id.description)
            val author = findViewById<TextView>(R.id.author)
            val date = findViewById<TextView>(R.id.date)

            Glide.with(this).load(article.urlToImage).into(newsImage)
            title.text = article.title
            description.text = article.description
            author.text = article.sourceModel?.name
            date.text = article.publishedAt

            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    private var onItemClickListener: ((ArticleModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (ArticleModel) -> Unit) {
        onItemClickListener = listener
    }

    fun addAllItem(articleModel: MutableList<ArticleModel>?) {
        list.clear()
        if (articleModel != null) {
            list.addAll(articleModel)
        }
        this.notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        this.notifyItemRemoved(position)
    }

    fun getItem(position: Int): ArticleModel? {
        return list.getOrNull(position)
    }
}