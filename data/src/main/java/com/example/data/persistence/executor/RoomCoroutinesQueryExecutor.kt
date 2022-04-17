package com.example.data.persistence.executor

import com.example.data.persistence.ApplicationDatabase
import com.example.domain.interactor.result.Result

interface RoomCoroutinesQueryExecutor {
    fun <T> executeQuery(performQuery: (ApplicationDatabase) -> T?): Result<T>
}