package com.phatherjay.pokedex.utils

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val FAVORITES = stringPreferencesKey("favorites")
    val PAGE = intPreferencesKey("page")
    val PAGESIZE = intPreferencesKey("pageSize")
    val QUERY = stringPreferencesKey("q")
}