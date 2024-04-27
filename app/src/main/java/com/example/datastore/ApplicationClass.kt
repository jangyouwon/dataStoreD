package com.example.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastore.datastore.DataStoreClass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class ApplicationClass:Application() {

    private lateinit var dataStore : DataStoreClass

    companion object {
        private lateinit var sampleApplication: ApplicationClass
        fun getInstance() : ApplicationClass = sampleApplication
    }

    override fun onCreate() {
        super.onCreate()
        sampleApplication = this
        dataStore = DataStoreClass(this)
    }

    fun getDataStore() : DataStoreClass = dataStore





}