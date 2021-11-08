package com.ekezet.portalgun.ui.screens.characters

import com.ekezet.portalgun.base.data.models.CharacterItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

internal object CharactersContract {
    interface ViewModel {
        val items: StateFlow<List<CharacterItem>>
        val maxItemCount: Flow<Int>
        @ExperimentalCoroutinesApi val lastError: Flow<Throwable?>

        fun fetchNextPage(): Job
        fun toggleFavorite(id: Int): Job
        fun fetchCharacterDetails(id: Int): Job
    }

    interface Router {
        fun showCharacterDetails(id: Int)
    }
}
