package com.example.data.persistence.dao

import androidx.room.*
import com.example.data.persistence.entity.ArticleRoomEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveArticle(article: ArticleRoomEntity)

    @Query("SELECT * FROM articles")
    fun getArticles() : Array<ArticleRoomEntity>

    @Delete
    fun deleteArticle(article: ArticleRoomEntity)
}