package com.phatherjay.pokedex.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val artist: String?,
    val attacks: List<Attack>?,
    @Json(name = "convertedRetreatCost")
    val convertedRetreatCost: Int?,
    val hp: String?,
    val id: String?,
    val images: Images?,
    val level: String?,
    val name: String?,
    val nationalPokedexNumbers: List<Int>?,
    val number: String?,
    val rarity: String?,
    val resistances: List<Resistance>?,
    val weaknesses: List<Weaknesse>?
)