package com.surli.surli_shinta_mvvm.data

import android.content.Context
import android.util.Log
import com.surli.surli_shinta_mvvm.data.local.SearchDatabase
import com.surli.surli_shinta_mvvm.data.model.Dog
import com.surli.surli_shinta_mvvm.data.model.DogData
import com.surli.surli_shinta_mvvm.data.model.DogResponse
import com.surli.surli_shinta_mvvm.data.remote.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(context: Context) {
    private val retrofit = ApiClient.instance
    private val database = SearchDatabase.instance(context)

    suspend fun getAllDogs(): DogResponse? {
        var result: DogResponse?

        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            result = try {
                retrofit.getAllDogs().body()
            } catch (e: Exception) {
                Log.d("Exception", e.message.toString())
                null
            }
        }
        return result
    }

    suspend fun saveAllDogs(characterData: List<DogData>): Boolean {
        var successInsert: Boolean
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val dogs = characterData.map {
                Dog(null, it.url)
            }
            successInsert = try {
                database.dog().insertAllDogs(dogs)
                true
            } catch (e: Exception) {
                false
            }
        }
        return successInsert
    }

    suspend fun loadAllDogs(): List<Dog> {
        var result: List<Dog>
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            result = database.dog().getAllDogs()
        }
        return result
    }
}