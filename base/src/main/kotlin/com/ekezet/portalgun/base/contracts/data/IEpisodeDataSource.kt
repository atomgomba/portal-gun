package com.ekezet.portalgun.base.contracts.data

import com.ekezet.portalgun.base.api.models.Episode

interface IEpisodeDataSource {
    suspend fun fetchItems(vararg ids: Int): List<Episode>
    suspend fun fetchItem(id: Int): Episode
}
