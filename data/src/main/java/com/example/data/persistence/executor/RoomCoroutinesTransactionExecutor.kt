package com.example.data.persistence.executor

import com.example.data.persistence.ApplicationDatabase
import com.example.domain.interactor.result.CompletableResult

interface RoomCoroutinesTransactionExecutor {
    fun executeQuery(performQuery: (ApplicationDatabase) -> Unit): CompletableResult
}