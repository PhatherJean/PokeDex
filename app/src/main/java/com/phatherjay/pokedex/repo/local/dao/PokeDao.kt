package com.phatherjay.pokedex.repo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.phatherjay.pokedex.model.Pokedex
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDao
{
    @Query("SELECT * FROM Pokedex")
    fun getAll(): Flow<List<Pokedex>>

    @Insert
    suspend fun insertAll(vararg kat: Pokedex)

    @Update
    suspend fun updateAll(kat: Pokedex)
}