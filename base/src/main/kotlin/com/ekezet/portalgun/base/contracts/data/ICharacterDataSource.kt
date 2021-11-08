package com.ekezet.portalgun.base.contracts.data

import com.ekezet.portalgun.base.api.models.Character
import com.ekezet.portalgun.base.api.models.Page
import kotlinx.coroutines.flow.StateFlow

interface ICharacterDataSource {
    val lastPageInfo: StateFlow<Page.Info?>

    suspend fun fetchPage(page: Int = 1): Page<Character>
    suspend fun fetchItem(id: Int): Character
}
