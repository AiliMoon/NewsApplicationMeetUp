package com.example.domain.model.news

import com.example.domain.model.news.ArticleModel
import java.io.Serializable

data class NewsModel(
    val articles: List<ArticleModel>?,
    val status: String?,
    val totalResults: Int?
) : Serializable