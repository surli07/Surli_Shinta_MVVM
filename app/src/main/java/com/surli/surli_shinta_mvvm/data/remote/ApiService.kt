package com.surli.surli_shinta_mvvm.data.remote

import com.surli.surli_shinta_mvvm.data.model.DogResponse
import retrofit2.Response
import retrofit2.http.GET
interface ApiService {

    @GET("dogs")
    suspend fun getAllDogs(): Response<DogResponse>
}