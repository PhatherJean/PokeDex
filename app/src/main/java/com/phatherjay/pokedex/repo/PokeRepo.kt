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
    fun getPokeState() = flow {
        Log.e(TAG, "api loading state")
        emit(ApiState.Loading)

        val pokeResponse = pokedexService.getCards()
        Log.e(TAG, "${pokeResponse.body()}")

        if (pokeResponse.isSuccessful){
            Log.e(TAG, "PokeResponse is successful")
            if (pokeResponse.body()!=null){
                Log.e(TAG,"Api End of page")
                ApiState.EndOfPage
            } else {
                Log.e(TAG, "Successfully got data")
                ApiState.Success(pokeResponse.body())
            }
        } else {
            Log.e(TAG,"Unable to get data")
            ApiState.Failure("Error Getting Data")
        }
        if (pokeResponse.body()==null){
            Log.e(TAG, "You goofed up somewhere back there")
        } else {
            emit(pokeResponse.body())
        }
}

//private val PokeQue.asQueryMap: Map<String, Any?>
//    get() = listOfNotNull(
//        "pageSize" to pageSize,
//        page?.let { "page" to it }
//    ).toMap()

    companion object {
        const val TAG = "REPO"
    }
}

private fun <T> Response<List<T>>.getApiState(): ApiState<List<T>> {
    return if (isSuccessful)
    {
        if (body().isNullOrEmpty()) { ApiState.EndOfPage }
        else { ApiState.Success(body()!!) }
    } else { ApiState.Failure("Error fetching data.") }
}