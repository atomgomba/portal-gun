package com.ekezet.portalgun.base.repos

import com.ekezet.portalgun.base.OpState
import com.ekezet.portalgun.base.contracts.data.IFavoriteDataSource
import com.ekezet.portalgun.base.contracts.repos.IFavoriteRepo
import com.ekezet.portalgun.base.contracts.repos.IFavoriteRepo.FavoriteUpdate
import com.ekezet.portalgun.base.di.MEM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
internal class FavoriteRepo(
    override val coroutineContext: CoroutineContext,
    private val memoryDataSource: IFavoriteDataSource,
) : IFavoriteRepo {

    private val _lastError: MutableStateFlow<Throwable?> = MutableStateFlow(null)
    @ExperimentalCoroutinesApi override val lastError: Flow<Throwable?> = _lastError

    private val _updates: MutableStateFlow<OpState<FavoriteUpdate>?> = MutableStateFlow(null)
    override val updates: Flow<OpState<FavoriteUpdate>> = _updates.filterNotNull()

    @Inject constructor(
        @Named(MEM) memoryDataSource: IFavoriteDataSource,
    ) : this(Dispatchers.IO, memoryDataSource)

    override suspend fun isFavorite(id: Int): Boolean =
        memoryDataSource.isFavorite(id)

    override suspend fun toggleFavorite(id: Int) {
        val updatedStatus = memoryDataSource.toggleFavorite(id)
        _updates.value = OpState.success(FavoriteUpdate(id, updatedStatus))
    }
}
