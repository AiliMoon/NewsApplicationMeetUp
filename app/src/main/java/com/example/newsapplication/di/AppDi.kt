package com.example.newsapplication.di

import com.example.newsapplication.view.fragment.article.ArticleViewModel
import com.example.newsapplication.view.fragment.breaking_news.NewsViewModel
import com.example.newsapplication.view.fragment.saved_news.SavedNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel { ArticleViewModel(saveArticle = get()) }
    viewModel { NewsViewModel(getBreakingNews = get(), searchNews = get()) }
    viewModel { SavedNewsViewModel(deleteArticle = get(), getArticles = get()) }
}