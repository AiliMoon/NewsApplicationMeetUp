package com.example.domain.interactor.usecase.news

import com.example.domain.interactor.result.Result
import com.example.domain.interactor.usecase.base.UseCase
import com.example.domain.model.news.ArticleModel
import com.example.domain.repository.NewsRepository

class GetArticles(
    private val repository: NewsRepository
) : UseCase<Array<ArticleModel>, Void>(){

    override suspend fun doOnBackground(params: Void?): Result<Array<ArticleModel>> {
        return repository.getArticles()
    }
}