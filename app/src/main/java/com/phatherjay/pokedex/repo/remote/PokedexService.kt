package com.phatherjay.pokedex.repo.remote

import com.phatherjay.pokedex.model.Pokedex
import retrofit2.Response
import retrofit2.http.*

interface PokedexService {

    @Headers("x-api-key:b726b688-457a-4b02-88f0-a5b2fbc27d9e")
    @GET("v2/cards")
    suspend fun getCards(@QueryMap options: Map<String, @JvmSuppressWildcards Any>) : Response<Pokedex>
}
