package com.phatherjay.pokedex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phatherjay.pokedex.model.Pokedex
import com.phatherjay.pokedex.model.requests.PokeQue
import com.phatherjay.pokedex.repo.PokeRepo
import com.phatherjay.pokedex.utils.ApiState
import com.phatherjay.pokedex.utils.PageAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeViewModel @Inject constructor(
    private val repo : PokeRepo
) : ViewModel() {
    private val _pokeState = MutableLiveData<ApiState<Pokedex>>()
    val pokeState: LiveData<ApiState<Pokedex>>
        get() = _pokeState

    var pokeQue: PokeQue? = null
    var currentPageAction = PageAction.FIRST
    private var currentPage = -1
    private var isNextPage = false

    private fun getPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getPokeState().collect{ poke ->

                _pokeState.postValue(poke as ApiState<Pokedex>?)
            }
        }
    }

    fun fetchPokeData(pokeQue: PokeQue) {
        Log.e("FetchPokeData", "inside with pokeQue")
        this.pokeQue = pokeQue
        fetchPokeData(PageAction.FIRST)
    }

    fun fetchPokeData(pageAction: PageAction) {
        if (_pokeState.value !is ApiState.Loading) pokeQue?.let { poke ->
            currentPageAction = pageAction
            poke.page = pageAction.update(poke.page ?: -1)
            val shouldFetchPage = isNextPage || pageAction == PageAction.FIRST
            if (shouldFetchPage) {
                currentPage = poke.page!!
                getPokemon()
            }
        }
    }

    private fun PageAction.update(page: Int) = when (this) {
        PageAction.FIRST -> 0
        PageAction.NEXT -> page.inc()
        PageAction.PREVIOUS -> if (page > 0) page.dec() else page
    }
}
