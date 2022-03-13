package com.surli.surli_shinta_mvvm.data.remote

import com.surli.surli_shinta_mvvm.data.model.DogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("images/search")
    suspend fun getAllDogs(
        @Query("size") size: String? = null,
        @Query("mime_types") mimeType: String? = null,
        @Query("format") format: String? = null,
        @Query("has_breeds") hasBreeds: Boolean? = null,
        @Query("order") order: String? = null,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null,
    ): Response<DogResponse>
}