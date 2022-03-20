package com.surli.surli_shinta_mvvm.data

import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.surli.surli_shinta_mvvm.data.local.SearchImageDao
import com.surli.surli_shinta_mvvm.data.model.Dog
import com.surli.surli_shinta_mvvm.data.remote.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val dogDao: SearchImageDao,
    private val ioDispatcher: CoroutineDispatcher
) {


    suspend fun getAllDogs(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {

        val response = apiService.getAllDogs()
        response.suspendOnSuccess {
            val result = ArrayList<Dog>()
            data.forEach { item ->
                result.add(Dog(null, item.url))
            }
            dogDao.insertAllDogs(result)
            emit(dogDao.getAllDogs())
        }.onError {
            onError(this.message().toString())
        }.onException {
            onError(message)
        }


    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)
}