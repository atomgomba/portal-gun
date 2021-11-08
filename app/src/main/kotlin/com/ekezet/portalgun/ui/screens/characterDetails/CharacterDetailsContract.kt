package com.ekezet.portalgun.ui.screens.characterDetails

import com.ekezet.portalgun.base.OpState
import com.ekezet.portalgun.base.data.models.CharacterItem
import com.ekezet.portalgun.base.data.models.EpisodeItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

object CharacterDetailsContract {
    interface ViewModel {
        val character: StateFlow<OpState<CharacterItem>>
        val episodes: StateFlow<OpState<List<EpisodeItem>>>
        @ExperimentalCoroutinesApi val lastError: Flow<Throwable?>

        fun toggleFavorite(id: Int): Job
    }

    interface Router {
        fun finish()
    }
}
