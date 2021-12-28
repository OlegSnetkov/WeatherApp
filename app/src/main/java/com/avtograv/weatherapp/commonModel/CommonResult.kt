package com.avtograv.weatherapp.commonModel


sealed class CommonResult<out T>

//internal object Loading : CommonResult<Nothing>()
//internal object NoLocation : CommonResult<Nothing>()

internal data class Success<T>(val data: T) : CommonResult<T>()
internal data class Failure(val exception: Throwable) : CommonResult<Nothing>()

internal inline fun <R> runCatchingResult(block: () -> R): CommonResult<R> {
    return try {
        Success(block())
    } catch (e: Throwable) {
        Failure(e)
    }
}