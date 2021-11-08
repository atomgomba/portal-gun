package com.ekezet.portalgun.base.repos

import com.ekezet.portalgun.base.OpState
import com.ekezet.portalgun.base.callOnFlow
import com.ekezet.portalgun.base.contracts.data.ICharacterDataSource
import com.ekezet.portalgun.base.contracts.repos.ICharacterRepo
import com.ekezet.portalgun.base.contracts.repos.IFavoriteRepo
import com.ekezet.portalgun.base.data.models.CharacterItem
import com.ekezet.portalgun.base.di.NET
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
internal class CharacterRepo(
    override val coroutineContext: CoroutineDispatcher,
    private val networkDataSource: ICharacterDataSource,
    private val favoriteRepo: IFavoriteRepo,
) : ICharacterRepo {

    @Inject constructor(
        @Named(NET) networkDataSource: ICharacterDataSource,
        favoriteRepo: IFavoriteRepo,
    ) : this(Dispatchers.IO, networkDataSource, favoriteRepo)

    override var lastRequestedPageNum: Int = -1

    override val maxItemCount: Flow<Int> = networkDataSource.lastPageInfo
        .filterNotNull()
        .map { it.count }

    private val backingItems = ConcurrentHashMap<Int, CharacterItem>()

    private val _items: MutableStateFlow<OpState<List<CharacterItem>>> =
        MutableStateFlow(OpState.idle())
    override val items: StateFlow<OpState<List<CharacterItem>>> = _items

    private val _item: MutableStateFlow<OpState<CharacterItem>> =
        MutableStateFlow(OpState.idle())
    override val item: StateFlow<OpState<CharacterItem>> = _item

    @ExperimentalCoroutinesApi
    override val lastError: Flow<Throwable?> = merge(items, item)
        .filter { it is OpState.Failure }
        .map { it.throwable }

    init {
        launch(coroutineContext) {
            favoriteRepo.updates
                .filter { it is OpState.Success }
                .mapNotNull { it.data }
                .collect { (id, state) ->
                    val item = backingItems[id] ?: return@collect
                    val updated = item.copy(isFavorite = state)
                    backingItems[id] = updated
                    _item.value = OpState.success(updated)
                    _items.value = OpState.success(backingItems.sorted())
                }
        }
    }

    override suspend fun fetchPage(pageNum: Int) = callOnFlow(_items) {
        val characters = networkDataSource.fetchPage(pageNum).results.map {
            (CharacterItem from it)
        }
        for (character in characters) {
            if (!backingItems.containsKey(character.id)) {
                backingItems[character.id] = character.withFavoriteStatus()
            }
        }
        lastRequestedPageNum = pageNum
        return@callOnFlow backingItems.sorted()
    }

    override suspend fun fetchItem(id: Int) = callOnFlow(_item) {
        backingItems[id]
            ?: (CharacterItem from networkDataSource.fetchItem(id)).withFavoriteStatus()
    }

    private suspend fun CharacterItem.withFavoriteStatus() = copy(
        isFavorite = favoriteRepo.isFavorite(id)
    )

    private fun ConcurrentHashMap<Int, CharacterItem>.sorted() = values.sortedBy { it.id }
}
