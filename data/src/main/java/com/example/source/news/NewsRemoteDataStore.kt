package com.example.source.news

import com.example.data.rest.client.RestClient
import com.example.data.entity.news.NewsEntity
import com.example.domain.interactor.result.CompletableResult
import com.example.domain.model.news.ArticleModel
import com.example.domain.interactor.result.Result

class NewsRemoteDataStore(
    private val restClient: RestClient
) : NewsDataStore {

    override fun getBreakingNews(): Result<NewsEntity> {
        return restClient.newsApiService().getBreakingNews()
    }

    override fun searchNews(searchQuery: String?): Result<NewsEntity> {
        return restClient.newsApiService().searchNews(searchQuery)
    }

    override fun saveArticle(articleModel: ArticleModel?): CompletableResult {
        throw UnsupportedOperationException()
    }

    override fun delete(articleModel: ArticleModel?): CompletableResult {
        throw UnsupportedOperationException()
    }

    override fun getArticles(): Result<Array<ArticleModel>> {
        throw UnsupportedOperationException()
    }
}