package com.example.coroutineretrofit.data
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private const val BASE_URL = "https://raw.githubusercontent.com"
    private fun initRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val countriesApi: CountriesApi = initRetrofit().create(CountriesApi::class.java)

}