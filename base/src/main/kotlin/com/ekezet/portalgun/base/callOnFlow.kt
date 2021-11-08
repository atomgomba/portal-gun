package com.ekezet.portalgun.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber

/**
 * @author kiri
 */
fun <R> CoroutineScope.callOnFlow(
    flow: MutableStateFlow<OpState<R>>,
    call: suspend () -> R,
) = async(coroutineContext) {
    flow.value = OpState.loading()
    try {
        val result = call()
        flow.value = OpState.success(result)
        return@async result
    } catch (t: Throwable) {
        Timber.w(t)
        flow.value = OpState.failure(t)
        return@async null
    }
}
