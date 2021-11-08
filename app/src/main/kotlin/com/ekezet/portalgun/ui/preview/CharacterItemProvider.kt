package com.ekezet.portalgun.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ekezet.portalgun.base.data.models.CharacterItem

class CharacterItemProvider : PreviewParameterProvider<CharacterItem> {
    override val values = sequenceOf(
        CharacterItem(
            1,
            "John Doe",
            CharacterItem.Status.ALIVE,
            "Human",
            CharacterItem.Origin("Earth", ""),
            "",
            emptyList()
        )
    )
}
