package com.ekezet.portalgun.base.api.models

import com.squareup.moshi.Json
import org.joda.time.DateTime

data class Episode(
    val id: Int,
    val name: String,
    @Json(name = "air_date")
    val airDate: DateTime,
    @Json(name = "episode")
    val seasonAndEpisode: String,
)
