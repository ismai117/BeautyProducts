package com.reachplc.interview.di

import com.reachplc.interview.data.remote.ProductsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModile {


    private val BASE_URL = "https://apps-tests.s3-eu-west-1.amazonaws.com/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {

        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().addInterceptor(logger).build()

    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()

    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ProductsService {

        return retrofit.create(ProductsService::class.java)

    }

}