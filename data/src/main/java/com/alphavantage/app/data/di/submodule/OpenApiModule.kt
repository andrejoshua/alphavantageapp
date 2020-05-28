package com.alphavantage.app.data.di.submodule

import com.alphavantage.app.data.remote.api.OpenApiService
import com.alphavantage.app.data.remote.implementation.OpenApiRepositoryImplementation
import com.alphavantage.app.domain.repository.open.OpenApiRepository
import org.koin.dsl.module
import retrofit2.Retrofit

fun provideOpenApiService(retrofit: Retrofit): OpenApiService =
    retrofit.create(OpenApiService::class.java)

fun provideOpenApiRepository(service: OpenApiService): OpenApiRepository =
    OpenApiRepositoryImplementation(service)

val openApiRepositoryModule = module {
    factory { provideOpenApiService(get()) }
    factory { provideOpenApiRepository(get()) }
}