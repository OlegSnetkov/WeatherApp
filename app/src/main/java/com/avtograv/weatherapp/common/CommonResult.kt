package com.avtograv.weatherapp.common


sealed class CommonResult<out T>

internal data class Success<T>(val data: T) : CommonResult<T>()
internal data class Failure(val exception: Throwable) : CommonResult<Nothing>()

internal inline fun <R> runCatchingResult(block: () -> R): CommonResult<R> {
    return try {
        Success(block())
    } catch (e: Throwable) {
        Failure(e)
    }
}