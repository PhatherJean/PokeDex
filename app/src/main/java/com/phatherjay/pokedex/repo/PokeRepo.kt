package com.phatherjay.pokedex.repo

import com.phatherjay.pokedex.model.Pokedex
import com.phatherjay.pokedex.model.requests.PokeQue
import com.phatherjay.pokedex.repo.local.dao.PokeDao
import com.phatherjay.pokedex.utils.ApiState
import com.phatherjay.pokedex.repo.remote.PokedexService
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class PokeRepo @Inject constructor(
    private val pokedexService: PokedexService,
    private val pokeDao: PokeDao
) {
    suspend fun getPokeState(pokeQue: PokeQue) : Flow<List<Pokedex>>{
        val pokeFlow = pokeDao.getAll()
        val pokeResp = pokedexService.getCards(pokeQue.asQueryMap)
        if (!pokeResp.body().isNullOrEmpty())
            pokeDao.insertAll(*pokeResp.body()!!.toTypedArray())
        return pokeFlow
    }


private val PokeQue.asQueryMap: Map<String, Any?>
    get() = listOfNotNull(
        "pageSize" to pageSize,
        page?.let { "page" to it }
    ).toMap()
}

private fun <T> Response<List<T>>.getApiState(): ApiState<List<T>> {
    return if (isSuccessful)
    {
        if (body().isNullOrEmpty()) { ApiState.EndOfPage }
        else { ApiState.Success(body()!!) }
    } else { ApiState.Failure("Error fetching data.") }
}