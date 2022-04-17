package com.example.data.entity.mapper.news

import com.example.data.entity.news.NewsEntity
import com.example.data.entity.mapper.base.SimpleEntityMapper
import com.example.domain.model.news.ArticleModel
import com.example.domain.model.news.NewsModel
import com.example.domain.model.news.SourceModel

class NewsEntityMapper : SimpleEntityMapper<NewsModel, NewsEntity>() {

    override fun transform(entity: NewsEntity): NewsModel {
        return NewsModel(
            articles = getNewsList(entity),
            status = entity.status,
            totalResults = entity.totalResults
        )
    }

    private fun getNewsList(entity: NewsEntity): List<ArticleModel> {
        val collection = mutableListOf<ArticleModel>()

        entity.articles?.forEach {
            val source = SourceModel(
                id = it.sourceEntity?.id,
                name = it.sourceEntity?.name
            )
            val article = ArticleModel(
                author = it.author,
                content = it.content,
                description = it.description,
                publishedAt = it.publishedAt,
                sourceModel = source,
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage
            )
            collection.add(article)
        }
        return collection
    }
}