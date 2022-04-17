package com.example.data.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
class ArticleRoomEntity(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: String?,
    val title: String?,
    @PrimaryKey(autoGenerate = false)
    val url: String = "",
    val urlToImage: String?,
    val isSaved: Boolean = false
)