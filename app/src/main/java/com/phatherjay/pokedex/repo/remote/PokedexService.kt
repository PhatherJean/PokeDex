package com.phatherjay.pokedex.repo.remote

import com.phatherjay.pokedex.model.Pokedex
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface PokedexService {

    @GET("/v2/cards")
    suspend fun getCards(@QueryMap options: Map<String, @JvmSuppressWildcards Any?>) : Response<List<Pokedex>>
}
