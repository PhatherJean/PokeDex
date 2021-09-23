package com.phatherjay.pokedex.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesX(
    @Json(name = "logo")
    val logo: String?,
    @Json(name = "symbol")
    val symbol: String?
)