package com.ekezet.portalgun.base.contracts.data

interface IFavoriteDataSource {
    suspend fun isFavorite(id: Int): Boolean
    suspend fun toggleFavorite(id: Int): Boolean
}
