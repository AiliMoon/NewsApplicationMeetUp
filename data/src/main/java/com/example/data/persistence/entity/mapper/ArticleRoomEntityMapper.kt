package com.example.data.persistence.entity.mapper

import com.example.data.persistence.entity.ArticleRoomEntity
import com.example.domain.model.news.ArticleModel
import com.example.domain.model.news.SourceModel
import com.example.data.entity.mapper.base.BaseEntityMapper

class ArticleRoomEntityMapper(
) : BaseEntityMapper<ArticleModel, ArticleRoomEntity>(){

    override fun transformToEntity(entity: ArticleRoomEntity?): ArticleModel {
        val model = SourceModel(entity?.source, entity?.source)
        return ArticleModel(
            author = entity?.author,
            content = entity?.content,
            description = entity?.description,
            publishedAt = entity?.publishedAt,
            sourceModel = model,
            title = entity?.title,
            url = entity?.url,
            urlToImage = entity?.urlToImage,
            isSaved = entity?.isSaved ?: false
        )
    }

    override fun transformToModel(entity: ArticleModel?): ArticleRoomEntity {
        return ArticleRoomEntity(
            author = entity?.author,
            content = entity?.content,
            description = entity?.description,
            publishedAt = entity?.publishedAt,
            source = entity?.sourceModel?.name,
            title = entity?.title,
            url = entity?.url ?: "",
            urlToImage = entity?.urlToImage,
            isSaved = entity?.isSaved ?: false
        )
    }
}