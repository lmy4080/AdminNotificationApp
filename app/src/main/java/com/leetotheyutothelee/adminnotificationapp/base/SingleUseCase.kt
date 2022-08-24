package com.leetotheyutothelee.adminnotificationapp.base

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

abstract class SingleUseCase<T, Params>() {

    abstract suspend fun buildUseCaseSingle(params: Params?): T

    suspend fun execute(
        params: Params? = null,
        onSuccess: (t: T) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        flow {
            emit(buildUseCaseSingle(params))
        }.onEach {
            onSuccess(it)
        }.catch {
            onError(it)
        }.collect()
    }
}