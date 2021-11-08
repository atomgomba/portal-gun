package com.ekezet.portalgun.ui.screens.characters.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import coil.compose.rememberImagePainter
import com.ekezet.portalgun.R
import com.ekezet.portalgun.base.data.models.CharacterItem
import com.ekezet.portalgun.ui.preview.CharacterItemProvider
import com.ekezet.portalgun.ui.theme.DEFAULT_PADDING
import com.ekezet.portalgun.ui.theme.Grey
import com.ekezet.portalgun.ui.theme.Purple700
import com.ekezet.portalgun.ui.theme.White
import com.ekezet.portalgun.ui.widgets.FavStar

@Composable @Preview internal fun CharacterListItem(
    @PreviewParameter(CharacterItemProvider::class) data: CharacterItem,
    onItemClick: ((id: Int) -> Unit)? = null,
    onFavoriteClick: ((id: Int) -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = White)
            .clickable { onItemClick?.invoke(data.id) }
            .padding(DEFAULT_PADDING),
    ) {
        data.run {
            Avatar(imageUrl)

            Column(modifier = Modifier.fillMaxWidth(.9f)) {
                Text(
                    name.uppercase(),
                    fontWeight = FontWeight.Bold,
                    color = if (isFavorite) {
                        Purple700
                    } else {
                        Color.Black
                    }
                )
                Text(listOf(statusString, species, origin.name).joinToString(" • "), color = Grey)
            }

            FavStar(
                id,
                isFavorite = isFavorite,
                onFavoriteClick = onFavoriteClick,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
        }
    }
}

@Composable private fun Avatar(url: String) {
    Image(
        painter = rememberImagePainter(
            data = url,
            builder = {
                placeholder(R.drawable.ic_avatar_placeholder)
            },
        ),
        contentDescription = null,
        modifier = Modifier
            .padding(end = DEFAULT_PADDING)
            .size(dimensionResource(id = R.dimen.avatarSize))
            .clip(CircleShape)

    )
}
