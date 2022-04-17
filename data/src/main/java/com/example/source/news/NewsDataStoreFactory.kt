package com.example.source.news

class NewsDataStoreFactory(
    private val remoteDataStore: NewsRemoteDataStore,
    private val localDataStore: NewsLocalDataStore
) {
    fun retrieveRemoteDataStore(): NewsRemoteDataStore {
        return remoteDataStore
    }

    fun retrieveLocalDataStore(): NewsLocalDataStore {
        return localDataStore
    }
}