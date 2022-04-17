package com.example.domain.interactor.usecase.news

import com.example.domain.interactor.result.Result
import com.example.domain.interactor.usecase.base.UseCase
import com.example.domain.model.news.NewsModel
import com.example.domain.repository.NewsRepository

class SearchNews(
    private val repository: NewsRepository
) : UseCase<NewsModel, String>(){
    override suspend fun doOnBackground(params: String?): Result<NewsModel> {
        return repository.searchNews(params)
    }
}