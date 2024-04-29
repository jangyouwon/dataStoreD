package com.example.datastore.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastore.MainActivity.Companion.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializer
import java.io.IOException

class DataStoreClass(private val context : Context) {

    private val Context.dataStore  by preferencesDataStore(name = "dataStoredd")

    private val pw_dataStore = stringPreferencesKey("password") // string 저장 키값
//    private val intKey = intPreferencesKey("key_name") // int 저장 키값


    suspend fun resetPreferenceData(key:String){

        context.dataStore.edit {
            it->it.remove(stringPreferencesKey(key))
        }
    }

    suspend fun setPreferenceData(key:String, data:String) {
        var preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey]= data
        }// String값을 stringKey 키 값에 저장
    }

    // stringKey 키 값과 대응되는 값 반환
    fun getPreferenceData(key:String):Flow<String>{
        var preferenceKey = stringPreferencesKey(key)
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {preferences ->
                preferences[preferenceKey] ?: ""
            }
    }
















    val pw_text : Flow<String> = context.dataStore.data
        .catch {exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            preferences[pw_dataStore] ?: ""
        }

    // String값을 stringKey 키 값에 저장
    suspend fun setPwText(text : String){
        context.dataStore.edit { preferences ->
            preferences[pw_dataStore] = text
        }
    }
}

