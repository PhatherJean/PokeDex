package com.phatherjay.pokedex.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Holofoil(
    @Json(name = "directLow")
    val directLow: Any?,
    @Json(name = "high")
    val high: Double?,
    @Json(name = "low")
    val low: Double?,
    @Json(name = "market")
    val market: Double?,
    @Json(name = "mid")
    val mid: Double?
)