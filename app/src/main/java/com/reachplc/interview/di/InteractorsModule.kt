package com.reachplc.interview.di

import com.reachplc.interview.data.remote.ProductResponseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {


    @Singleton
    @Provides
    fun provideProductResponseMappper(): ProductResponseMapper {
        return ProductResponseMapper()
    }


}