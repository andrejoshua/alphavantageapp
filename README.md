
# AlphaVantage App

**AlphaVantage** is just an example of forex (currency) calculator Android application with Clean Architecture approach, integrated with [AlphaVantage API](https://www.alphavantage.co/). This application is still being maintained to keep updated with current technology standards.

## Features (Pages)

As the time of writing, the application only has 2 pages, main pages, and currency pages. Since [AlphaVantage](https://www.alphavantage.co/) doesn't provide the list of currencies, so i fetched it from [here](https://openexchangerates.org/api/currencies.json). Note: not all currencies can be calculated.

This project also uses API 16 as minimum API level.

## Architecture

![Module Structure](https://github.com/andrejoshua/alphavantageapp/blob/master/addons/module-structure.png?raw=true)

This architecture mostly follows the mix of [this](https://github.com/android10/Android-CleanArchitecture) and [this](https://github.com/igorwojda/android-showcase). See those links for detailed explanation about Clean Architecture.

## Architectural Approach & Separation of Concern

![Detailed Class Structure](https://github.com/andrejoshua/alphavantageapp/blob/master/addons/class-structure.png?raw=true)

There are few additional points i will explain given the picture:
* LiveData\<Event\> is basically a LiveData that will not retrieve any values until the previous value is consumed. Refer to [this](https://github.com/andrejoshua/alphavantageapp/blob/master/domain/src/main/java/com/alphavantage/app/domain/widget/Event.kt).
* This app uses Navigation components to communicate between Fragments, but directly from ViewModel without sending callback to its Fragment. [NavManager](https://github.com/andrejoshua/alphavantageapp/blob/master/app/src/main/java/com/alphavantage/app/nav/NavManager.kt) class contains `navigate(NavDirections)` to navigate current fragment to next destination fragment and `getArgs(): NavArgs?` to get arguments from previous fragment.
* `retrieveNetwork()` is a function to integrate repository with asynchronous Flow (which will be converted to LiveData). `retrieveNetworkAndSync()` is basically previously mentioned function with synchronization to local repository. See [Usecase](https://github.com/andrejoshua/alphavantageapp/blob/master/domain/src/main/java/com/alphavantage/app/domain/usecase/UseCase.kt) class.
* Any exceptions retrieved from API client (Retrofit) will be handled in `getResult()`. See [BaseImplementation](https://github.com/andrejoshua/alphavantageapp/blob/master/data/src/main/java/com/alphavantage/app/data/remote/implementation/BaseImplementation.kt) class.


## Project Libraries

* [Kotlin 1.3.72](https://kotlinlang.org/docs/reference/android-overview.html)
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html)
* [Koin DI](https://start.insert-koin.io/#/quickstart/kotlin)
* [AndroidX Jetpack](https://developer.android.com/jetpack)
	* [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) + [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data)
	* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
	* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
	* [DataBinding](https://developer.android.com/topic/libraries/data-binding)
* [ObjectBox ORM](https://docs.objectbox.io/kotlin-support)
* [Retrofit](https://square.github.io/retrofit/)
* [JUnit + Mockito](https://developer.android.com/training/testing/unit-testing/local-unit-tests)
