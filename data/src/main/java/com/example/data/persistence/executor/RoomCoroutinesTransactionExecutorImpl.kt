package com.example.data.persistence.executor

import com.example.data.persistence.ApplicationDatabase
import com.example.domain.interactor.result.CompletableResult

class RoomCoroutinesTransactionExecutorImpl(
    private val database: ApplicationDatabase
) : RoomCoroutinesTransactionExecutor {

    override fun executeQuery(performQuery: (ApplicationDatabase) -> Unit): CompletableResult {
        return try {
            performQuery(database)
            CompletableResult.Success()
        } catch (t: Throwable) {
            CompletableResult.Exception(t)
        }
    }
}
