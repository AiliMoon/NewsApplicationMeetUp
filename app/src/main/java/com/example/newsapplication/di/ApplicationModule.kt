package com.example.newsapplication.di

import android.content.Context
import com.example.data.entity.mapper.news.NewsEntityMapper
import com.example.data.persistence.entity.mapper.ArticleRoomEntityMapper
import com.example.data.persistence.executor.RoomExecutorsProvider
import com.example.data.persistence.executor.RoomExecutorsProviderImpl
import com.example.data.persistence.provider.RoomProvider
import com.example.data.persistence.provider.RoomProviderImpl
import com.example.data.repository.NewsRepositoryImpl
import com.example.data.rest.client.RestClient
import com.example.data.rest.client.RestClientImpl
import com.example.domain.interactor.usecase.news.*
import com.example.domain.repository.NewsRepository
import com.example.newsapplication.view.fragment.article.ArticleViewModel
import com.example.newsapplication.view.fragment.breaking_news.NewsViewModel
import com.example.newsapplication.view.fragment.saved_news.SavedNewsViewModel
import com.example.source.news.NewsDataStoreFactory
import com.example.source.news.NewsLocalDataStore
import com.example.source.news.NewsRemoteDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val retrofitModule = module {
    single { provideRestClient() }
}

val repositoryModule = module {
    single { newsDataMapper() }
    single { provideRoomExecutors(get()) }
    single { provideNewsRepository(
        dataStoreFactory = get(),
        newsEntityMapper = get()) }
    single { provideNewsRemoteDataStore(restClient = get())}
    single { provideLocalDataStore(roomExecutorsProvider = get(), articleRoomEntityMapper = get())}

    single { provideRoomDatabase(androidContext()) }
}

val roomModule = module {
    single { articleRoomMapper() }
    factory { provideDataStoreFactory(remoteDataStore = get(), localDataStore = get())}
}

val viewModels = module {
    factory { provideSaveArticleUseCase(repository = get()) }
    factory { provideDeleteArticleUseCase(repository = get()) }
    factory { provideGetArticlesUseCase(repository = get()) }
    factory { provideGetBreakingNewsUseCase(repository = get()) }
    factory { provideSearchNewsUseCase(repository = get()) }
    viewModel { ArticleViewModel(saveArticle = get()) }
    viewModel { NewsViewModel(getBreakingNews = get(), searchNews = get()) }
    viewModel { SavedNewsViewModel(deleteArticle = get(), getArticles = get())}
}

private fun provideRestClient(): RestClient {
    return RestClientImpl()
}

private fun provideNewsRepository(
    dataStoreFactory: NewsDataStoreFactory,
    newsEntityMapper: NewsEntityMapper
): NewsRepository {
    return NewsRepositoryImpl(dataStoreFactory, newsEntityMapper)
}

fun provideRoomDatabase(context: Context): RoomProvider {
    return RoomProviderImpl(context)
}
fun provideRoomExecutors(roomProvider: RoomProvider): RoomExecutorsProvider {
    return RoomExecutorsProviderImpl(roomProvider)
}

fun provideSaveArticleUseCase(repository: NewsRepository) = SaveArticle(repository)
fun provideDeleteArticleUseCase(repository: NewsRepository) = DeleteArticle(repository)
fun provideGetArticlesUseCase(repository: NewsRepository) = GetArticles(repository)
fun provideGetBreakingNewsUseCase(repository: NewsRepository) = GetBreakingNews(repository)
fun provideSearchNewsUseCase(repository: NewsRepository) = SearchNews(repository)
fun provideNewsRemoteDataStore(restClient: RestClient) = NewsRemoteDataStore(restClient)

fun provideDataStoreFactory(remoteDataStore: NewsRemoteDataStore, localDataStore: NewsLocalDataStore) = NewsDataStoreFactory(remoteDataStore, localDataStore)
fun provideLocalDataStore(roomExecutorsProvider: RoomExecutorsProvider, articleRoomEntityMapper: ArticleRoomEntityMapper) = NewsLocalDataStore(roomExecutorsProvider, articleRoomEntityMapper)
fun newsDataMapper() = NewsEntityMapper()
fun articleRoomMapper() = ArticleRoomEntityMapper()