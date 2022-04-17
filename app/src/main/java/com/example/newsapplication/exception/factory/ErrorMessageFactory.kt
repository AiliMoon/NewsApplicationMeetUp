package com.example.newsapplication.exception.factory

import com.example.data.exception.ConnectionLostException

class ErrorMessageFactory {

    companion object {
        fun create(throwable: Throwable): String? {
            if (throwable is ConnectionLostException) {
                return "Проверьте интернет соедниение"
            }

            return throwable.localizedMessage
        }

    }
}