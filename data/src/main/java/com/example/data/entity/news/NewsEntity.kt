package com.example.data.entity.news

import com.google.gson.annotations.SerializedName

data class NewsEntity(
    @SerializedName("articles")
    val articles: List<ArticleEntity>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
)