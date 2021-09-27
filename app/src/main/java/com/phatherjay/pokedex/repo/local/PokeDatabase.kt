package com.phatherjay.pokedex.repo.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.phatherjay.pokedex.model.Pokedex
import com.phatherjay.pokedex.repo.local.dao.PokeDao
import com.phatherjay.pokedex.repo.local.utils.PokeConvertors

@Database(entities = [Pokedex::class], version = 1)
@TypeConverters(PokeConvertors::class)
abstract class PokeDatabase : RoomDatabase()
{
    abstract fun pokeDao(): PokeDao
    companion object
    {
        private const val DATABASE_NAME = "poke.db"
        @Volatile private var instance : PokeDatabase? = null

        fun getInstance(context: Context): PokeDatabase
        {
            return instance ?: synchronized(this)
            {
                instance ?: buildDatabase(context).also { instance = it}
            }
        }

        private fun buildDatabase(context: Context): PokeDatabase
        {
            return Room.databaseBuilder(
                context, PokeDatabase::class.java, DATABASE_NAME
            ).addTypeConverter(PokeConvertors()).build()
        }
    }
}
