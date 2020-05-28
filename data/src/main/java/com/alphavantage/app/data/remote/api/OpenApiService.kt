package com.alphavantage.app.data.remote.api

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface OpenApiService {

    @GET
    suspend fun getCurrencies(
        @Url url: String
    ): Response<JsonElement>
}