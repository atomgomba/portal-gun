package com.ekezet.portalgun.ui.screens.characterDetails.layouts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.ekezet.portalgun.base.OpState
import com.ekezet.portalgun.base.data.models.EpisodeItem
import com.ekezet.portalgun.ui.theme.SCREEN_PADDING

@Composable fun EpisodeList(eps: OpState<List<EpisodeItem>>) {
    when (eps) {
        is OpState.Loading -> Text(
            "Loading episodes…",
            modifier = Modifier
                .padding(SCREEN_PADDING)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        is OpState.Success -> {
            LazyColumn {
                items(
                    items = eps.data,
                    key = { it.id },
                ) { ep ->
                    EpisodeListItem(ep)
                }
            }
        }
        is OpState.Failure -> Text(eps.throwable.localizedMessage ?: "Error")
        else -> {
            // ignored
        }
    }
}
