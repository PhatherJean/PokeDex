package com.phatherjay.pokedex.di

import android.content.Context
import androidx.room.Database
import com.phatherjay.pokedex.repo.local.PokeDatabase
import com.phatherjay.pokedex.repo.local.dao.PokeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule{
    @Singleton
    @Provides
    fun providesPokeDb(@ApplicationContext context: Context) : PokeDatabase = PokeDatabase.getInstance(context)

    @Provides
    fun providesPokeDao(database: PokeDatabase) : PokeDao = database.pokeDao()
}