package com.ekezet.portalgun.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekezet.portalgun.base.OpState
import com.ekezet.portalgun.base.contracts.repos.ICharacterRepo
import com.ekezet.portalgun.base.contracts.repos.IFavoriteRepo
import com.ekezet.portalgun.base.data.models.CharacterItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CharactersViewModel @Inject constructor(
    private val characterRepo: ICharacterRepo,
    private val favoriteRepo: IFavoriteRepo,
) : ViewModel(), CharactersContract.ViewModel {

    private val _items = MutableStateFlow<List<CharacterItem>>(emptyList())
    override val items: StateFlow<List<CharacterItem>> = _items

    override val maxItemCount: Flow<Int> = characterRepo.maxItemCount

    @ExperimentalCoroutinesApi
    override val lastError: Flow<Throwable?> = merge(characterRepo.lastError, favoriteRepo.lastError)

    private var nextPage: Int = -1

    init {
        viewModelScope.launch {
            characterRepo.items
                .mapNotNull { it as? OpState.Success }
                .collect { result ->
                    _items.value = result.data
                    nextPage = characterRepo.lastRequestedPageNum + 1
                }
        }
    }

    override fun fetchNextPage() = viewModelScope.launch {
        characterRepo.fetchPage(nextPage).await()
    }

    override fun toggleFavorite(id: Int): Job = viewModelScope.launch {
        favoriteRepo.toggleFavorite(id)
    }

    override fun fetchCharacterDetails(id: Int): Job = viewModelScope.launch {
        characterRepo.fetchItem(id).await()
    }
}
