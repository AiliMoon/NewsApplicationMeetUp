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
import com.example.domain.repository.NewsRepository
import com.example.source.news.NewsDataStoreFactory
import com.example.source.news.NewsLocalDataStore
import com.example.source.news.NewsRemoteDataStore
import org.koin.dsl.module

val dataModule = module {
    single { provideNewsRemoteDataStore(restClient = get()) }
    single { ArticleRoomEntityMapper() }
    single { NewsEntityMapper() }
    single { provideNewsRepository(dataStoreFactory = get(), newsEntityMapper = get())}
    single { provideRoomProvider(context = get())}
    single { provideRoomExecutorProvider(roomProvider = get())}
    single { NewsLocalDataStore(roomExecutorsProvider = get(), articleRoomEntityMapper = get())}
    factory { provideDataStoreFactory(remoteDataStore = get(), localDataStore = get())}
}

val retrofitModule = module {
    single<RestClient> { RestClientImpl() }
}

private fun provideNewsRepository(dataStoreFactory: NewsDataStoreFactory, newsEntityMapper: NewsEntityMapper) : NewsRepository {
    return NewsRepositoryImpl(dataStoreFactory, newsEntityMapper)
}
private fun provideRoomProvider(context: Context) : RoomProvider {
    return RoomProviderImpl(context)
}

private fun provideRoomExecutorProvider(roomProvider: RoomProvider) : RoomExecutorsProvider {
    return RoomExecutorsProviderImpl(roomProvider)
}

private fun provideNewsRemoteDataStore(restClient: RestClient) = NewsRemoteDataStore(restClient)
private fun provideDataStoreFactory(remoteDataStore: NewsRemoteDataStore, localDataStore: NewsLocalDataStore) = NewsDataStoreFactory(remoteDataStore, localDataStore)