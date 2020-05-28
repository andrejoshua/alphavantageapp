package com.alphavantage.app.data.di.submodule

import com.alphavantage.app.data.local.implementation.ForexLocalRepositoryImplementation
import com.alphavantage.app.data.remote.api.ForexService
import com.alphavantage.app.data.remote.implementation.ForexRemoteRepositoryImplementation
import com.alphavantage.app.domain.repository.forex.ForexLocalRepository
import com.alphavantage.app.domain.repository.forex.ForexRemoteRepository
import org.koin.dsl.module
import retrofit2.Retrofit

fun provideForexService(retrofit: Retrofit): ForexService =
    retrofit.create(ForexService::class.java)

fun provideForexRemoteRepository(service: ForexService): ForexRemoteRepository =
    ForexRemoteRepositoryImplementation(service)

fun provideForexLocalRepository(): ForexLocalRepository = ForexLocalRepositoryImplementation()

val generalRepositoryModule = module {
    factory { provideForexService(get()) }
    factory { provideForexRemoteRepository(get()) }
    factory { provideForexLocalRepository() }
}