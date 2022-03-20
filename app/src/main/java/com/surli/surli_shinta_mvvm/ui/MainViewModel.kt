package com.surli.surli_shinta_mvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surli.surli_shinta_mvvm.data.MainRepository
import com.surli.surli_shinta_mvvm.data.model.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _dog = MutableLiveData<List<Dog>>()
    val dog: LiveData<List<Dog>> = _dog
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message


    suspend fun fetchAndLoadDogs() {
        viewModelScope.launch(ioDispatcher) {
            repository.getAllDogs(
                onStart = { _loading.postValue(true) },
                onComplete = { _loading.postValue(false) },
                onError = { _message.postValue(it) }
            ).collect {
                val imageDog = it
                _dog.postValue(imageDog)
            }
        }
    }
}