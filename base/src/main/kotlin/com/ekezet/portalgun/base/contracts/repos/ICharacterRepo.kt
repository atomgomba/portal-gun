package com.ekezet.portalgun.base.contracts.repos

import com.ekezet.portalgun.base.OpState
import com.ekezet.portalgun.base.data.models.CharacterItem
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ICharacterRepo : IRepo {
    val items: StateFlow<OpState<List<CharacterItem>>>
    val item: StateFlow<OpState<CharacterItem>>
    val maxItemCount: Flow<Int>
    var lastRequestedPageNum: Int

    suspend fun fetchPage(pageNum: Int = 1): Deferred<List<CharacterItem>?>
    suspend fun fetchItem(id: Int): Deferred<CharacterItem?>
}
