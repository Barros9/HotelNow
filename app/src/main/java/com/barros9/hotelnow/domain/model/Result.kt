package com.barros9.hotelnow.domain.model

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
}

suspend fun <T> getResult(invoke: suspend () -> T): Result<T> {
    return runCatching {
        Result.Success(invoke())
    }.getOrElse {
        Result.Error(it)
    }
}