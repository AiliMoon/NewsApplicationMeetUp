package com.example.domain.model.news

import java.io.Serializable

data class ArticleModel(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val sourceModel: SourceModel?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    val isSaved: Boolean = false
) : Serializable