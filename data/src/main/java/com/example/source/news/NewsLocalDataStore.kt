package com.example.source.news

import com.example.data.entity.news.NewsEntity
import com.example.data.persistence.entity.mapper.ArticleRoomEntityMapper
import com.example.data.persistence.executor.RoomExecutorsProvider
import com.example.domain.interactor.result.CompletableResult
import com.example.domain.model.news.ArticleModel
import com.example.domain.interactor.result.Result

class NewsLocalDataStore(
    private val roomExecutorsProvider: RoomExecutorsProvider,
    private val articleRoomEntityMapper: ArticleRoomEntityMapper
) : NewsDataStore {

    override fun getBreakingNews(): Result<NewsEntity> {
        throw UnsupportedOperationException()
    }

    override fun searchNews(searchQuery: String?): Result<NewsEntity> {
        throw UnsupportedOperationException()
    }

    override fun saveArticle(articleModel: ArticleModel?): CompletableResult {
        val model = articleRoomEntityMapper.transformToModel(articleModel)
        return roomExecutorsProvider.provideTransactionExecutor().executeQuery { db ->
            db.newsDao().saveArticle(model)
        }
    }

    override fun delete(articleModel: ArticleModel?): CompletableResult {
        val model = articleRoomEntityMapper.transformToModel(articleModel)
        return roomExecutorsProvider.provideTransactionExecutor().executeQuery { db ->
            db.newsDao().deleteArticle(model)
        }
    }

    override fun getArticles(): Result<Array<ArticleModel>> {
        return roomExecutorsProvider.provideQueryExecutor().executeQuery { db ->
            articleRoomEntityMapper.transformToEntityCollection(
                db.newsDao().getArticles().toMutableList()
            ).toTypedArray()
        }
    }
}