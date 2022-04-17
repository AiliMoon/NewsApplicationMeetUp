package com.example.data.persistence.executor

import com.example.data.persistence.ApplicationDatabase
import com.example.data.exception.RoomObjectNotFound
import com.example.domain.interactor.result.Result

class RoomCoroutinesQueryExecutorImpl(
    private val database: ApplicationDatabase
) : RoomCoroutinesQueryExecutor {

    override fun <T> executeQuery(performQuery: (ApplicationDatabase) -> T?): Result<T> {
        val result = performQuery(database)

        return if (result == null) {
            Result.Exception(RoomObjectNotFound())
        } else {
            Result.Success(result)
        }
    }
}