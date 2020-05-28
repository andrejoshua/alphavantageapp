package com.alphavantage.app.domain.di

import com.alphavantage.app.domain.di.forex.generalModules
import com.alphavantage.app.domain.di.general.openApiModules

val domainModules = listOf(generalModules, openApiModules)