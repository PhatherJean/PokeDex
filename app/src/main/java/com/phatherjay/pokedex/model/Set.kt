package com.phatherjay.pokedex.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Set(
    @Json(name = "id")
    val id: String?,
    @Json(name = "images")
    val images: ImagesX?,
    @Json(name = "legalities")
    val legalities: LegalitiesX?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "printedTotal")
    val printedTotal: Int?,
    @Json(name = "ptcgoCode")
    val ptcgoCode: String?,
    @Json(name = "releaseDate")
    val releaseDate: String?,
    @Json(name = "series")
    val series: String?,
    @Json(name = "total")
    val total: Int?,
    @Json(name = "updatedAt")
    val updatedAt: String?
)