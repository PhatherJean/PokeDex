package com.phatherjay.pokedex.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Legalities(
    @Json(name = "unlimited")
    val unlimited: String?
)