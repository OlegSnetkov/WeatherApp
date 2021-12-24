package com.avtograv.weatherapp

sealed class Result<out T>

internal object Loading : Result<Nothing>()
internal object NoLocation : Result<Nothing>()
internal data class Success<T>(val data: T) : Result<T>()
internal data class Failure(val exception: Throwable) : Result<Nothing>()

internal inline fun <R> runCatchingResult(block: () -> R): Result<R> {
    return try {
        Success(block())
    } catch (e: Throwable) {
        Failure(e)
    }
}