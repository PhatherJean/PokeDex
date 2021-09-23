package com.phatherjay.pokedex.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Prices(
    @Json(name = "averageSellPrice")
    val averageSellPrice: Double?,
    @Json(name = "avg1")
    val avg1: Double?,
    @Json(name = "avg30")
    val avg30: Double?,
    @Json(name = "avg7")
    val avg7: Double?,
    @Json(name = "germanProLow")
    val germanProLow: Any?,
    @Json(name = "lowPrice")
    val lowPrice: Double?,
    @Json(name = "lowPriceExPlus")
    val lowPriceExPlus: Double?,
    @Json(name = "reverseHoloAvg1")
    val reverseHoloAvg1: Double?,
    @Json(name = "reverseHoloAvg30")
    val reverseHoloAvg30: Double?,
    @Json(name = "reverseHoloAvg7")
    val reverseHoloAvg7: Double?,
    @Json(name = "reverseHoloLow")
    val reverseHoloLow: Double?,
    @Json(name = "reverseHoloSell")
    val reverseHoloSell: Any?,
    @Json(name = "reverseHoloTrend")
    val reverseHoloTrend: Double?,
    @Json(name = "suggestedPrice")
    val suggestedPrice: Any?,
    @Json(name = "trendPrice")
    val trendPrice: Double?
)