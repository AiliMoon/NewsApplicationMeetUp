package com.example.domain.repository

import com.example.domain.interactor.result.CompletableResult
import com.example.domain.model.news.ArticleModel
import com.example.domain.model.news.NewsModel
import com.example.domain.interactor.result.Result

interface NewsRepository {

    fun getBreakingNews() : Result<NewsModel>
    fun searchNews(searchQuery: String?) : Result<NewsModel>
    fun saveArticle(articleModel: ArticleModel?) : CompletableResult
    fun delete(articleModel: ArticleModel?) : CompletableResult
    fun getArticles() : Result<Array<ArticleModel>>
}