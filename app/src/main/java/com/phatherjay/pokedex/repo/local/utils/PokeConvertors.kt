package com.phatherjay.pokedex.repo.local.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.phatherjay.pokedex.model.Data
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class PokeConvertors{

    private val moshi by lazy { Moshi.Builder().build() }

    private inline fun <reified T> getGenericAdapterTwo(): JsonAdapter<T> {
        val type = Types.newParameterizedType(T::class.java)
        return moshi.adapter<T>(type)
    }

    private inline fun <reified T> getGenericAdapter(): JsonAdapter<List<T>>? {
        val type = Types.newParameterizedType(List::class.java, T::class.java)
        return moshi.adapter<List<T>>(type)
    }

    @TypeConverter
    fun dataToString(something: List<Data>) : String? {
        return getGenericAdapter<Data>()?.toJson(something)
    }

    @TypeConverter
    fun stringToData(data: String?) : List<Data>? {
        return data?.let { getGenericAdapter<Data>()?.fromJson(it) }
    }
}