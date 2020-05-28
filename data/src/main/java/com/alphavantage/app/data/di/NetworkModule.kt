package com.alphavantage.app.data.di

import com.alphavantage.app.data.BuildConfig
import com.alphavantage.app.data.remote.ApiConstants
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(ApiConstants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(ApiConstants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(ApiConstants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            var request = chain.request()
            val url = when (request.url.host) {
                "www.alphavantage.co" -> request.url.newBuilder().addQueryParameter(
                    "apikey",
                    BuildConfig.API_TOKEN
                ).build()
                else -> request.url.newBuilder().build()
            }

            request = request.newBuilder().header(
                "Cache-Control",
                "public, max-age=" + 5
            ).url(url).build()

            chain.proceed(request)
        }
        .build()
}

fun provideTypeAdapter(): TypeAdapterFactory {
    return object : TypeAdapterFactory {

        override fun <T : Any?> create(gson: Gson?, type: TypeToken<T>?): TypeAdapter<T> {
            val delegate = gson!!.getDelegateAdapter(this, type)
            val elementAdapter = gson.getAdapter(JsonElement::class.java)

            return object : TypeAdapter<T>() {

                override fun write(out: JsonWriter?, value: T) {
                    delegate.write(out, value)
                }

                override fun read(`in`: JsonReader?): T {
                    val jsonElement = elementAdapter.read(`in`)
                    if (jsonElement.isJsonObject) {
                        val jsonObject = jsonElement.asJsonObject
                        if (jsonObject.has("Error Message"))
                            throw Exception(jsonObject.get("Error Message").asString)

                        if (jsonObject.has("Information"))
                            throw Exception(jsonObject.get("Information").asString)

                        if (jsonObject.has("Note"))
                            throw Exception(jsonObject.get("Note").asString)
                    }

                    return delegate.fromJsonTree(jsonElement)
                }
            }.nullSafe()
        }
    }
}

fun provideGson(typeAdapterFactory: TypeAdapterFactory): Gson {
    return GsonBuilder()
        .registerTypeAdapterFactory(typeAdapterFactory)
        .create()
}

fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.API_BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

val networkModule = module {
    factory { provideHttpClient() }
    factory { provideTypeAdapter() }
    factory { provideGson(get()) }
    single { provideRetrofit(get(), get()) }
}