package com.phatherjay.pokedex.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@Entity
@JsonClass(generateAdapter = true)
data class Pokedex(
    @PrimaryKey val id : String,
    @Json(name = "data")
    val `data`: Data?
)