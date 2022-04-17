package com.example.domain.interactor.usecase.news

import com.example.domain.interactor.result.CompletableResult
import com.example.domain.interactor.usecase.base.CompletableUseCase
import com.example.domain.model.news.ArticleModel
import com.example.domain.repository.NewsRepository

class DeleteArticle(
    private val repository: NewsRepository
) : CompletableUseCase<ArticleModel>() {

    override suspend fun doOnBackground(params: ArticleModel?): CompletableResult {
        return repository.delete(params)
    }
}