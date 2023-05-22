package com.example.flavorquest.di

import android.util.Log
import com.example.flavorquest.core.Constants.BASE_URL
import com.example.flavorquest.core.Constants.OK_HTTP
import com.example.flavorquest.data.remote.network.RecipeServices
import com.example.flavorquest.data.repository.RecipeRepositoryImpl
import com.example.flavorquest.domain.RecipeRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DataModule {

    fun load() {
        loadKoinModules(repositoryModule() + networkModule())
    }

    private fun repositoryModule(): Module {
        return module {
            single<RecipeRepository> {
                RecipeRepositoryImpl(get())
            }
        }
    }

    private fun networkModule(): Module {
        return module {
            single<RecipeServices> {
                createService(get(), get())
            }
            single {
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            }
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }
        }
    }

    private inline fun <reified T> createService(
        factory: Moshi,
        client: OkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .client(client)
            .build()
            .create(T::class.java)
    }
}