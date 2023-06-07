package com.example.flavorquest.di

import android.util.Log
import com.example.flavorquest.core.Constants.BASE_URL
import com.example.flavorquest.core.Constants.OK_HTTP
import com.example.flavorquest.data.remote.network.RecipeServices
import com.example.flavorquest.data.repository.RecipeRepositoryImpl
import com.example.flavorquest.domain.repository.RecipeRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {
    
    fun load() {
        loadKoinModules(repositoryModule() + networkModule())
    }
    
    private fun repositoryModule(): Module {
        return module {
            single<RecipeRepository> {
                RecipeRepositoryImpl(service = get())
            }
        }
    }
    
    private fun networkModule(): Module {
        return module {
            single<RecipeServices> {
                createService(client = get())
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
    
    private fun createService(
        client: OkHttpClient
    ): RecipeServices {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RecipeServices::class.java)
    }
    
}