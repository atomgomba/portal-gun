package com.ekezet.portalgun.ui.screens.characters.layouts

import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.ekezet.portalgun.ui.screens.characters.CharactersContract
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable internal fun CharactersScreen(
    viewModel: CharactersContract.ViewModel,
    router: CharactersContract.Router,
) {
    val items = viewModel.items.collectAsState()
    val maxItemCount = viewModel.maxItemCount.collectAsState(initial = 0)
    val lastError = viewModel.lastError.collectAsState(initial = null).value

    CharacterList(
        items, maxItemCount,
        onLastItemReached = viewModel::fetchNextPage,
        onItemClick = { id ->
            viewModel.fetchCharacterDetails(id)
            router.showCharacterDetails(id)
        },
        onFavoriteClick = viewModel::toggleFavorite,
    )

    if (lastError != null) {
        Snackbar {
            Text(lastError.localizedMessage ?: "Unknown error")
        }
    }
}
