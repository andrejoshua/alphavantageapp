package com.alphavantage.app.data.remote.implementation

import com.alphavantage.app.domain.model.Result
import retrofit2.Response
import timber.log.Timber

abstract class BaseImplementation {

    protected suspend fun <T1,T2> getResult(call: suspend () -> Response<T1>, mapper: (T1) -> T2): Result<T2> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val mapped = mapper(body)
                    return Result.success(mapped)
                }
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        Timber.e(message)
        return Result.error("Call failed: $message")
    }
}