package com.ekezet.portalgun.base.contracts.repos

import com.ekezet.portalgun.base.OpState
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepo : IRepo {
    val updates: Flow<OpState<FavoriteUpdate>>

    suspend fun isFavorite(id: Int): Boolean
    suspend fun toggleFavorite(id: Int)

    data class FavoriteUpdate(val id: Int, val isFavorite: Boolean)
}
