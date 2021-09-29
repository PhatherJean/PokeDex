package com.phatherjay.pokedex.repo.local.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.phatherjay.pokedex.model.requests.PokeQue
import com.phatherjay.pokedex.utils.PreferenceKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


@Singleton
class UserPrefManager @Inject constructor(@ApplicationContext val context: Context)
{
    private val dataStore by lazy {context.dataStore}
    val pokeQue
        get() = dataStore.data.map { preferences ->
            preferences[PreferenceKeys.FAVORITES]?.let {
                PokeQue(
                    pageSize = preferences[PreferenceKeys.PAGESIZE] ?: 10,
                    page = preferences[PreferenceKeys.PAGE] ?: 1,
                    q = preferences[PreferenceKeys.QUERY] ?: ""
                )
            }
        }
    suspend fun saveQuery()
    {
    }
}