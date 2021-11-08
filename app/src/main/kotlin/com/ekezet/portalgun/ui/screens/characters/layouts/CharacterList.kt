package com.ekezet.portalgun.ui.screens.characters.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.ekezet.portalgun.base.data.models.CharacterItem
import com.ekezet.portalgun.ui.theme.DEFAULT_PADDING
import com.ekezet.portalgun.ui.theme.SCREEN_PADDING

@Composable internal fun CharacterList(
    list: State<List<CharacterItem>>,
    maxItemCount: State<Int>,
    onLastItemReached: suspend () -> Unit,
    onItemClick: (id: Int) -> Unit,
    onFavoriteClick: (id: Int) -> Unit,
) {
    val items = list.value
    val maxCount = maxItemCount.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            itemsIndexed(
                items = items,
                key = { _, item -> item.id },
            ) { index, item ->
                CharacterListItem(item, onItemClick, onFavoriteClick)

                if (index == items.lastIndex) {
                    Text("Loading more…", modifier = Modifier.padding(SCREEN_PADDING).fillMaxWidth(), textAlign = TextAlign.Center)

                    LaunchedEffect(index) {
                        onLastItemReached()
                    }
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                "${items.size}/$maxCount",
                modifier = Modifier
                    .padding(DEFAULT_PADDING)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}
