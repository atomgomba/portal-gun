package com.ekezet.portalgun.ui.screens.characterDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekezet.portalgun.base.OpState
import com.ekezet.portalgun.base.contracts.repos.ICharacterRepo
import com.ekezet.portalgun.base.contracts.repos.IEpisodeRepo
import com.ekezet.portalgun.base.contracts.repos.IFavoriteRepo
import com.ekezet.portalgun.base.data.models.CharacterItem
import com.ekezet.portalgun.base.data.models.EpisodeItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CharacterDetailsViewModel @Inject constructor(
    private val characterRepo: ICharacterRepo,
    private val episodeRepo: IEpisodeRepo,
    private val favoriteRepo: IFavoriteRepo,
) : ViewModel(), CharacterDetailsContract.ViewModel {

    private val _character = MutableStateFlow<OpState<CharacterItem>>(OpState.idle())
    override val character: StateFlow<OpState<CharacterItem>> = _character

    private val _episodes = MutableStateFlow<OpState<List<EpisodeItem>>>(OpState.idle())
    override val episodes: StateFlow<OpState<List<EpisodeItem>>> = _episodes

    @ExperimentalCoroutinesApi override val lastError: Flow<Throwable?> =
        merge(characterRepo.lastError, episodeRepo.lastError, favoriteRepo.lastError)

    init {
        viewModelScope.launch {
            characterRepo.item
                .collect { result ->
                    _character.value = result
                    if (result is OpState.Success) {
                        episodeRepo.fetchEpisodes(*result.data.episodeIds).await()
                    }
                }
        }

        viewModelScope.launch {
            episodeRepo.items
                .collect { result -> _episodes.value = result }
        }
    }

    override fun toggleFavorite(id: Int) = viewModelScope.launch {
        favoriteRepo.toggleFavorite(id)
    }
}
