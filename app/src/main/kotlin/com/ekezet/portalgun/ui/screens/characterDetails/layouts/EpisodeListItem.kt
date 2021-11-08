package com.ekezet.portalgun.ui.screens.characterDetails.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import com.ekezet.portalgun.base.data.models.EpisodeItem
import com.ekezet.portalgun.ui.theme.Grey

@Composable fun EpisodeListItem(item: EpisodeItem) {
    Column {
        Text(item.name, fontWeight = Bold)
        Text("${item.seasonAndEpisode} • ${item.airDateString}", color = Grey)
    }
}
