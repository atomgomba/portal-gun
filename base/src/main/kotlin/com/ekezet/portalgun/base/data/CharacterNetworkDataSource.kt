package com.ekezet.portalgun.base.data

import com.ekezet.portalgun.base.api.models.Character
import com.ekezet.portalgun.base.api.models.Page
import com.ekezet.portalgun.base.api.services.MicroverseService
import com.ekezet.portalgun.base.contracts.data.ICharacterDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterNetworkDataSource @Inject constructor(
    private val microverseService: MicroverseService,
) : ICharacterDataSource {
    private val _lastPageInfo = MutableStateFlow<Page.Info?>(null)
    override val lastPageInfo: StateFlow<Page.Info?> = _lastPageInfo

    override suspend fun fetchPage(page: Int): Page<Character> {
        val result = microverseService.getCharactersByPage(page)
        _lastPageInfo.value = result.info
        return result
    }

    override suspend fun fetchItem(id: Int): Character =
        microverseService.getCharacter(id)
}
