package com.ekezet.portalgun.ui.screens.characterDetails.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import coil.compose.rememberImagePainter
import com.ekezet.portalgun.R
import com.ekezet.portalgun.base.OpState
import com.ekezet.portalgun.base.data.models.CharacterItem
import com.ekezet.portalgun.ui.preview.CharacterItemProvider
import com.ekezet.portalgun.ui.screens.characterDetails.CharacterDetailsContract
import com.ekezet.portalgun.ui.theme.DEFAULT_PADDING
import com.ekezet.portalgun.ui.theme.SCREEN_PADDING
import com.ekezet.portalgun.ui.theme.White
import com.ekezet.portalgun.ui.widgets.FavStar

@Composable internal fun CharacterDetailsScreen(
    viewModel: CharacterDetailsContract.ViewModel,
) {
    val item = viewModel.character.collectAsState()
    val eps = viewModel.episodes.collectAsState()

    Column(
        modifier = Modifier
            .background(White)
            .padding(SCREEN_PADDING)
            .fillMaxSize()
    ) {
        item.value.let { result ->
            when (result) {
                is OpState.Success -> {
                    CharacterInfo(result.data) { id ->
                        viewModel.toggleFavorite(id)
                    }
                }
                is OpState.Failure -> {
                    Text(result.throwable.localizedMessage ?: "Unknown Error")
                }
                else -> {
                    // ignore
                }
            }
        }

        val episodes = eps.value

        Text(
            if (episodes !is OpState.Success) {
                "Episodes"
            } else {
                "Episodes (${episodes.data.size})"
            },
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = DEFAULT_PADDING),
            style = MaterialTheme.typography.h4,
        )

        episodes.let { eps ->
            EpisodeList(eps)
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
            .size(dimensionResource(R.dimen.avatarSize) * 2)
            .aspectRatio(1f)
            .clip(CircleShape)

    )
}

@Composable @Preview private fun CharacterInfo(
    @PreviewParameter(CharacterItemProvider::class) item: CharacterItem,
    onFavoriteClick: ((id: Int) -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .background(White)
            .fillMaxWidth()
    ) {

        Text(
            item.name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = DEFAULT_PADDING),
            style = MaterialTheme.typography.h2,
        )

        Row {
            Box {
                Avatar(item.imageUrl)
                FavStar(item.id, isFavorite = item.isFavorite, onFavoriteClick = onFavoriteClick)
            }

            val textStyle = MaterialTheme.typography.subtitle1
            val rowModifier =
                Modifier.padding(
                    start = SCREEN_PADDING,
                    end = SCREEN_PADDING,
                    bottom = DEFAULT_PADDING
                )

            Column {
                Row(modifier = rowModifier) {
                    Text("Status: ", style = textStyle, fontWeight = FontWeight.Bold)
                    Text(item.statusString, style = textStyle)
                }

                Row(modifier = rowModifier) {
                    Text("Species: ", style = textStyle, fontWeight = FontWeight.Bold)
                    Text(item.species, style = textStyle)
                }

                Row(modifier = rowModifier) {
                    Text("Origin: ", style = textStyle, fontWeight = FontWeight.Bold)
                    Text(item.origin.name, style = textStyle)
                }
            }
        }
    }
}
