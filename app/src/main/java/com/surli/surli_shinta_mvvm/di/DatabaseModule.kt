package com.surli.surli_shinta_mvvm.di

import android.app.Application
import androidx.room.Room
import com.surli.surli_shinta_mvvm.data.local.SearchDatabase
import com.surli.surli_shinta_mvvm.data.local.SearchImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): SearchDatabase {
        return Room.databaseBuilder(
            application,
            SearchDatabase::class.java,
            "search_database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun SearchImageDao(searchDatabase: SearchDatabase): SearchImageDao {
        return searchDatabase.dog()
    }

}