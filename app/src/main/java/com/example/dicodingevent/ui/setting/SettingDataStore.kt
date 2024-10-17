package com.example.dicodingevent.ui.setting

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

private val THEME_KEY = booleanPreferencesKey("theme_setting")

class SettingDataStore private constructor(private val dataStore: DataStore<Preferences>) {
    fun getThemeSettings(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSettings(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SettingDataStore? = null
        fun getInstance(dataStore: DataStore<Preferences>): SettingDataStore {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SettingDataStore(dataStore).also {
                    INSTANCE = it
                }
            }
        }
    }
}