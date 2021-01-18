package com.example.coroutineretrofit.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coroutineretrofit.data.ApiService
import com.example.coroutineretrofit.model.Country
import kotlinx.coroutines.*

class ListViewModel : ViewModel() {
    val countries = MutableLiveData<ArrayList<Country>>()
    val countryLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception ${throwable.localizedMessage}")
    }
    var job: Job? = null

    fun refresh() {
        loading.value = true

        generateDummyCountries()
    }

    private fun generateDummyCountries() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = ApiService.countriesApi.getCountries()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    countries.value = response.body()
                    loading.value = false
                    countryLoadError.value = null
                } else {
                    countryLoadError.value = response.message()
                    loading.value = false
                }
            }
        }
    }

    private fun onError(message: String) {
        countryLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}