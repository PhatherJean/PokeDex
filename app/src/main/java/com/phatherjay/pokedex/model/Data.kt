package com.phatherjay.pokedex.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val artist: String?,
    val attacks: List<Attack>?,
    @Json(name = "cardmarket")
    val cardMarket: CardMarket?,
    @Json(name = "convertedRetreatCost")
    val convertedRetreatCost: Int?,
    val hp: String?,
    val id: String?,
    val images: Images?,
    val legalities: Legalities?,
    val level: String?,
    val name: String?,
    val nationalPokedexNumbers: List<Int>?,
    val number: String?,
    val rarity: String?,
    val resistances: List<Resistance>?,
    @Json(name = "retreatCost")
    val retreatCost: List<String>?,
    val `set`: Set?,
    @Json(name = "subtypes")
    val subtypes: List<String>?,
    @Json(name = "supertype")
    val supertype: String?,
    @Json(name = "tcgplayer")
    val tcgPlayer: TcgPlayer?,
    @Json(name = "types")
    val types: List<String>?,
    val weaknesses: List<Weaknesse>?
)