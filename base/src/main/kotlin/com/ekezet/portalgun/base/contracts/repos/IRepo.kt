package com.ekezet.portalgun.base.contracts.repos

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface IRepo : CoroutineScope {
    @ExperimentalCoroutinesApi
    val lastError: Flow<Throwable?>
}
