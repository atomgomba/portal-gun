package com.ekezet.portalgun.base.api.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
 * @author kiri
 */
object DateTimeAdapter {
    private val pattern = DateTimeFormat.forPattern("MMMM dd, yyyy")

    @ToJson fun toJson(value: DateTime) = value.toString(pattern)
    @FromJson fun fromJson(value: String): DateTime = DateTime.parse(value, pattern)
}
