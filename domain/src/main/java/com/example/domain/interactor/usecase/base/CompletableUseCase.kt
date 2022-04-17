package com.example.domain.interactor.usecase.base

import com.example.domain.interactor.result.CompletableResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class CompletableUseCase<Params> {

    abstract suspend fun doOnBackground(params: Params?): CompletableResult

    fun execute(
        scope: CoroutineScope,
        params: Params? = null,
        onResult: (CompletableResult) -> Unit
    ) {
        scope.launch {
            val deferred = async(Dispatchers.IO) {
                doOnBackground(params)
            }
            onResult(deferred.await())
        }
    }
}