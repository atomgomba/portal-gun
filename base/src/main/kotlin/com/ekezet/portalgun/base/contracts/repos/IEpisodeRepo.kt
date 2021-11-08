package com.ekezet.portalgun.base.contracts.repos

import com.ekezet.portalgun.base.OpState
import com.ekezet.portalgun.base.data.models.EpisodeItem
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.StateFlow

interface IEpisodeRepo : IRepo {
    val items: StateFlow<OpState<List<EpisodeItem>>>

    suspend fun fetchEpisodes(vararg ids: Int): Deferred<List<EpisodeItem>?>
}
