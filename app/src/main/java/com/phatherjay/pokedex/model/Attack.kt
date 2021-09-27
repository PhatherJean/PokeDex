package com.phatherjay.pokedex.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Attack(
    @Json(name = "damage")
    val damage: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "text")
    val text: String?
)