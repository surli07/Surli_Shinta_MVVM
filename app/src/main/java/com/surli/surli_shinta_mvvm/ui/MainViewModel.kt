package com.surli.surli_shinta_mvvm.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.surli.surli_shinta_mvvm.data.MainRepository
import com.surli.surli_shinta_mvvm.data.model.Dog
import com.surli.surli_shinta_mvvm.data.model.DogData

class MainViewModel(
    private val repository: MainRepository

) : ViewModel() {

    var view: MainView? = null
    val databaseDog = MutableLiveData<List<Dog>>()


    suspend fun fetchAndLoadDogs() {
        view?.showLoading()
        val result = repository.getAllDogs()
        result?.let {
            val dogs = ArrayList<DogData>()
            it.forEach {  response ->
                dogs.add(
                    DogData(
                        response.breeds,
                        response.height,
                        response.id,
                        response.url,
                        response.width,
                    )
                )
            }
            repository.saveAllDogs(dogs)
        }

        val loadFromDatabase = repository.loadAllDogs()
        databaseDog.postValue(loadFromDatabase)
        view?.hideLoading()

    }

}