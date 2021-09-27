package com.phatherjay.pokedex.repo

import android.util.Log
import com.phatherjay.pokedex.model.Pokedex
import com.phatherjay.pokedex.model.requests.PokeQue
import com.phatherjay.pokedex.repo.local.dao.PokeDao
import com.phatherjay.pokedex.utils.ApiState
import com.phatherjay.pokedex.repo.remote.PokedexService
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class PokeRepo @Inject constructor(
    private val pokedexService: PokedexService,
    private val pokeDao: PokeDao
) {
    //    suspend fun getPokeState(pokeQue: PokeQue) : Flow<List<Pokedex>>{
//        val pokeFlow = pokeDao.getAll()
//        val pokeResp = pokedexService.getCards(pokeQue.asQueryMap)
//        if (!pokeResp.body().isNullOrEmpty())
//            pokeDao.insertAll(*pokeResp.body()!!.toTypedArray())
//        return pokeFlow
//    }
    fun getPokeState(pokeQue: PokeQue) = flow {
        Log.e(TAG, "api loading state")
        emit(ApiState.Loading)


        val state = if(pokeQue.page != null) {
            val pokeResponse = pokedexService.getCards(pokeQue.asQueryMap)
            if (pokeResponse.isSuccessful){
                Log.e(TAG, "Response is successful")
                if (pokeResponse.body() == null){
                    ApiState.EndOfPage
                }else {
                    Log.e(TAG, "getPokeState: Success")
                    ApiState.Success(pokeResponse.body())
                }
            } else {
                Log.e(TAG, "getPokeState is a failure")
                ApiState.Failure("error fetching data")
            }
        }else ApiState.Failure("Endpoint is a dud")
        emit(state)
}

private val PokeQue.asQueryMap: Map<String, Any>
    get() = listOfNotNull(
        pageSize?.let { "pageSize" to it },
        page?.let { "page" to it }
    ).toMap()

    companion object {
        const val TAG = "REPO"
    }
}

