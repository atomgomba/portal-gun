package com.ekezet.portalgun.base.api.models

import com.squareup.moshi.Json

data class Character(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val origin: Origin,
    @Json(name = "image") val imageUrl: String,
    @Json(name = "episode") val episodeUrls: List<String>,
) {
    enum class Status {
        @Json(name = "Alive")
        ALIVE,
        @Json(name = "Dead")
        DEAD,
        @Json(name = "unknown")
        UNKNOWN,
    }

    data class Origin(val name: String, val url: String)
}
