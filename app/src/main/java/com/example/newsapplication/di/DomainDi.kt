package com.example.newsapplication.di

import com.example.domain.interactor.usecase.news.*
import com.example.domain.repository.NewsRepository
import org.koin.dsl.module

val domainModule = module {
    factory { provideSaveArticleUseCase(repository = get()) }
    factory { provideDeleteArticleUseCase(repository = get()) }
    factory { provideGetArticlesUseCase(repository = get()) }
    factory { provideGetBreakingNewsUseCase(repository = get()) }
    factory { provideSearchNewsUseCase(repository = get()) }
}

private fun provideSaveArticleUseCase(repository: NewsRepository) = SaveArticle(repository)
private fun provideDeleteArticleUseCase(repository: NewsRepository) = DeleteArticle(repository)
private fun provideGetArticlesUseCase(repository: NewsRepository) = GetArticles(repository)
private fun provideGetBreakingNewsUseCase(repository: NewsRepository) = GetBreakingNews(repository)
private fun provideSearchNewsUseCase(repository: NewsRepository) = SearchNews(repository)