package com.ekezet.portalgun.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ekezet.portalgun.R

@Composable fun FavStar(
    id: Int,
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
    onFavoriteClick: ((id: Int) -> Unit)? = null,
) {
    Icon(
        painter = painterResource(id = if (isFavorite) R.drawable.ic_baseline_star_24 else R.drawable.ic_baseline_star_outline_24),
        contentDescription = null,
        modifier = modifier
            .clickable { onFavoriteClick?.invoke(id) }
            .size(48.dp),
    )
}
