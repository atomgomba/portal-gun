package com.ekezet.portalgun.base.data

import com.ekezet.portalgun.base.api.models.Episode
import com.ekezet.portalgun.base.api.services.MicroverseService
import com.ekezet.portalgun.base.contracts.data.IEpisodeDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodeNetworkDataSource @Inject constructor(
    private val microverseService: MicroverseService,
) : IEpisodeDataSource {

    override suspend fun fetchItem(id: Int): Episode =
        microverseService.getEpisode(id)

    override suspend fun fetchItems(vararg ids: Int): List<Episode> =
        microverseService.getSomeEpisodes(ids.joinToString(","))
}
