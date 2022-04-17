package com.example.domain.interactor.result

sealed class Result<out T> {

    class Success<T>(val element: T) : Result<T>() {
        override fun <E> map(transform: (T) -> E): Result<E> {
            return Success(transform(element))
        }
    }

    class Exception(val throwable: Throwable) : Result<Nothing>() {
        override fun <E> map(transform: (Nothing) -> E): Result<E> {
            return this
        }
    }

    abstract fun <E> map(transform: (T) -> E): Result<E>

    fun perform(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit) {
        when (this) {
            is Success -> onSuccess(element)
            is Exception -> onError(throwable)
        }
    }
}

