package com.example.domain.interactor.usecase.news

import com.example.domain.interactor.result.Result
import com.example.domain.interactor.usecase.base.UseCase
import com.example.domain.model.news.NewsModel
import com.example.domain.repository.NewsRepository

class GetBreakingNews(
    private val repository: NewsRepository
) : UseCase<NewsModel, Void>() {
    override suspend fun doOnBackground(params: Void?): Result<NewsModel> {
        return repository.getBreakingNews()
    }
}