package com.phatherjay.pokedex.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PricesX(
    @Json(name = "holofoil")
    val holofoil: Holofoil?,
    @Json(name = "reverseHolofoil")
    val reverseHolofoil: ReverseHolofoil?
)