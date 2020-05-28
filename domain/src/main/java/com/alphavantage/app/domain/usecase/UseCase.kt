package com.alphavantage.app.domain.usecase

import com.alphavantage.app.domain.model.Result
import com.alphavantage.app.domain.widget.DispatcherProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

abstract class UseCase {

    protected fun <T> retrieveNetworkAndSync(
        dbQuery: () -> T?,
        networkCall: suspend () -> Result<T>,
        saveCallResult: suspend (T) -> Unit,
        dispatcherProvider: DispatcherProvider
    ) = flow {
        emit(Result.loading())

        val dbRes = withContext(dispatcherProvider.main()) { dbQuery.invoke() }
        val call = withContext(dispatcherProvider.default()) { networkCall.invoke() }

        if (call.status == Result.Status.SUCCESS) {
            emit(call)

            withContext(dispatcherProvider.default()) {
                saveCallResult(call.data!!)
            }
        } else {
            emit(Result.error(call.message!!))

            if (dbRes != null) {
                emit(Result.success(dbRes))
            }
        }
    }

    protected fun <T> retrieveNetwork(
        networkCall: suspend () -> Result<T>,
        dispatcherProvider: DispatcherProvider
    ) = flow {
        emit(Result.loading(null))

        val call =
            withContext(dispatcherProvider.default()) { networkCall.invoke() }

        if (call.status == Result.Status.SUCCESS) {
            emit(Result.success(call.data!!))
        } else {
            emit(Result.error(call.message!!, null))
        }
    }
}