package com.ekezet.portalgun.base.data

import com.ekezet.portalgun.base.contracts.data.IFavoriteDataSource
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteMemoryDataSource @Inject constructor() : IFavoriteDataSource {

    private val favorites = ConcurrentHashMap<Int, Boolean>()

    override suspend fun isFavorite(id: Int): Boolean =
        favorites.getOrDefault(id, false)

    override suspend fun toggleFavorite(id: Int): Boolean {
        val negated = isFavorite(id).not()
        favorites[id] = negated
        return negated
    }
}
