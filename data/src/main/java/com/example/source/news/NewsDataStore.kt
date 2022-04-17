package com.example.source.news

import com.example.data.entity.news.NewsEntity
import com.example.domain.interactor.result.CompletableResult
import com.example.domain.model.news.ArticleModel
import com.example.domain.interactor.result.Result

interface NewsDataStore {

    fun getBreakingNews() : Result<NewsEntity>
    fun searchNews(searchQuery: String?) : Result<NewsEntity>
    fun saveArticle(articleModel: ArticleModel?) : CompletableResult
    fun delete(articleModel: ArticleModel?) : CompletableResult
    fun getArticles() : Result<Array<ArticleModel>>
}