package com.phatherjay.pokedex.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TcgPlayer(
    @Json(name = "prices")
    val prices: PricesX?,
    @Json(name = "updatedAt")
    val updatedAt: String?,
    @Json(name = "url")
    val url: String?
)