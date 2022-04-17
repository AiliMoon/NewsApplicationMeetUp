package com.example.data.persistence.executor

interface RoomExecutorsProvider {
    fun provideQueryExecutor(): RoomCoroutinesQueryExecutor
    fun provideTransactionExecutor(): RoomCoroutinesTransactionExecutor
}
