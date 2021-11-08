package com.ekezet.portalgun.base.data.models

import com.ekezet.portalgun.base.api.models.Character

data class CharacterItem(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val origin: Origin,
    val imageUrl: String,
    val episodeUrls: List<String>,
    var isFavorite: Boolean = false,
) {
    val episodeIds: IntArray = episodeUrls
        .toSet()
        .mapNotNull { url ->
            val lastPos = url.lastIndexOf('/')
            if (lastPos == -1 || lastPos == url.length - 1) {
                null
            } else {
                url.substring(lastPos + 1)
            }
        }
        .map { it.toInt() }
        .toIntArray()

    val statusString = status.name.lowercase().capitalize()

    enum class Status {
        ALIVE,
        DEAD,
        UNKNOWN;

        companion object {
            infix fun from(other: Character.Status) = when (other) {
                Character.Status.ALIVE -> ALIVE
                Character.Status.DEAD -> DEAD
                Character.Status.UNKNOWN -> UNKNOWN
            }
        }
    }

    data class Origin(val name: String, val url: String) {
        companion object {
            infix fun from(other: Character.Origin) = Origin(other.name, other.url)
        }
    }

    companion object {
        infix fun from(other: Character) = CharacterItem(
            id = other.id,
            name = other.name,
            status = Status from other.status,
            species = other.species,
            origin = Origin from other.origin,
            imageUrl = other.imageUrl,
            episodeUrls = other.episodeUrls,
        )
    }
}
