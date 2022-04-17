package com.example.data.persistence.executor

import com.example.data.persistence.provider.RoomProvider

class RoomExecutorsProviderImpl(
    private val roomProvider: RoomProvider
) : RoomExecutorsProvider {

    override fun provideQueryExecutor(): RoomCoroutinesQueryExecutor {
        return RoomCoroutinesQueryExecutorImpl(roomProvider.provide())
    }

    override fun provideTransactionExecutor(): RoomCoroutinesTransactionExecutor {
        return RoomCoroutinesTransactionExecutorImpl(roomProvider.provide())
    }
}
