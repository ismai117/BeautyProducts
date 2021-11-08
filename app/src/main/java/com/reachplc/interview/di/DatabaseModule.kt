package com.reachplc.interview.di

import android.content.Context
import androidx.room.Room
import com.reachplc.interview.data.local.ProductDao
import com.reachplc.interview.data.local.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ProductDatabase::class.java,
            ProductDatabase.TABLE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideProductDao(productDatabase: ProductDatabase): ProductDao {
        return productDatabase.getProductDao()
    }


}