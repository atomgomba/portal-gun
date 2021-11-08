package com.ekezet.portalgun.base.data.models

import com.ekezet.portalgun.base.api.models.Episode
import org.joda.time.DateTime

data class EpisodeItem(
    val id: Int,
    val name: String,
    val airDate: DateTime,
    val seasonAndEpisode: String,
) {
    val airDateString = airDate.toString(DT_PATTERN)

    companion object {
        const val DT_PATTERN = "YYYY/MM/dd"

        infix fun from(other: Episode) = EpisodeItem(
            id = other.id,
            name = other.name,
            airDate = other.airDate,
            seasonAndEpisode = other.seasonAndEpisode,
        )
    }
}
