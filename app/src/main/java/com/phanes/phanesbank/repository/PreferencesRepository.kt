package com.phanes.phanesbank.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile

class PreferencesRepository private constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {

        private var INSTANCE: PreferencesRepository? = null

        fun initialize(context: Context) {
            val dataStore = PreferenceDataStoreFactory.create {
                context.preferencesDataStoreFile("settings")
            }

            INSTANCE = PreferencesRepository(dataStore)
        }

        fun get() : PreferencesRepository {
            return INSTANCE ?: throw IllegalStateException(
                "Preferences must be initialized"
            )
        }
    }
}