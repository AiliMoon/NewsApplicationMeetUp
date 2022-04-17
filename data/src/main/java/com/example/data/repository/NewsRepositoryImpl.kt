package com.example.data.repository

import com.example.source.news.NewsDataStoreFactory
import com.example.domain.interactor.result.CompletableResult
import com.example.domain.model.news.ArticleModel
import com.example.domain.model.news.NewsModel
import com.example.data.entity.mapper.news.NewsEntityMapper
import com.example.domain.repository.NewsRepository
import com.example.domain.interactor.result.Result

class NewsRepositoryImpl(
    private val dataStoreFactory: NewsDataStoreFactory,
    private val newsEntityMapper: NewsEntityMapper
) : NewsRepository {

    override fun getBreakingNews(): Result<NewsModel> {
        return dataStoreFactory.retrieveRemoteDataStore().getBreakingNews().map {
            newsEntityMapper.transform(it)
        }
    }

    override fun searchNews(searchQuery: String?): Result<NewsModel> {
        return dataStoreFactory.retrieveRemoteDataStore().searchNews(searchQuery).map {
            newsEntityMapper.transform(it)
        }
    }

    override fun saveArticle(articleModel: ArticleModel?): CompletableResult {
        return dataStoreFactory.retrieveLocalDataStore().saveArticle(articleModel)
    }

    override fun delete(articleModel: ArticleModel?): CompletableResult {
        return dataStoreFactory.retrieveLocalDataStore().delete(articleModel)
    }

    override fun getArticles(): Result<Array<ArticleModel>> {
        return dataStoreFactory.retrieveLocalDataStore().getArticles()
    }
}