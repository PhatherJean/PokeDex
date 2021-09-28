package com.phatherjay.pokedex.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Images(
    @Json(name = "large")
    val large: String?,
    @Json(name = "small")
    val small: String?
)