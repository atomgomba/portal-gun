package com.ekezet.portalgun.base.repos

import com.ekezet.portalgun.base.OpState
import com.ekezet.portalgun.base.callOnFlow
import com.ekezet.portalgun.base.contracts.data.IEpisodeDataSource
import com.ekezet.portalgun.base.contracts.repos.IEpisodeRepo
import com.ekezet.portalgun.base.data.models.EpisodeItem
import com.ekezet.portalgun.base.di.NET
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
internal class EpisodeRepo(
    override val coroutineContext: CoroutineDispatcher,
    private val networkDataSource: IEpisodeDataSource,
) : IEpisodeRepo {

    private val _items: MutableStateFlow<OpState<List<EpisodeItem>>> =
        MutableStateFlow(OpState.idle())
    override val items: StateFlow<OpState<List<EpisodeItem>>> = _items

    private val backingItems = ConcurrentHashMap<Int, EpisodeItem>()

    @ExperimentalCoroutinesApi override val lastError: Flow<Throwable?> = items
        .filter { it is OpState.Failure }
        .map { it.throwable }

    @Inject constructor(@Named(NET) networkDataSource: IEpisodeDataSource) :
        this(Dispatchers.IO, networkDataSource)

    override suspend fun fetchEpisodes(vararg ids: Int) = callOnFlow(_items) {
        val unique = ids.toSet()
        val missingIds = unique.filter { !backingItems.containsKey(it) }.toIntArray()
        if (missingIds.isNotEmpty()) {
            if (1 == missingIds.size) {
                val ep = networkDataSource.fetchItem(missingIds.first())
                backingItems[ep.id] = EpisodeItem from ep
            } else {
                networkDataSource.fetchItems(*missingIds).map { ep ->
                    backingItems[ep.id] = EpisodeItem from ep
                }
            }
        }
        unique.mapNotNull { id -> backingItems[id] }.sorted()
    }

    private fun List<EpisodeItem>.sorted() = sortedBy { ep -> ep.seasonAndEpisode }
}
