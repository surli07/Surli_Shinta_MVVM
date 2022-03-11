package com.surli.surli_shinta_mvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.surli.surli_shinta_mvvm.local.SearchImage
import com.surli.surli_shinta_mvvm.local.SearchImageDao

@Database(entities = [SearchImage::class], version = 1)
abstract class SearchDatabase : RoomDatabase() {

    abstract fun searchImage(): SearchImageDao

    companion object {
        private var database: SearchDatabase? = null

        fun instance(context: Context): SearchDatabase {
            if (database == null){
                synchronized(SearchDatabase::class){
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        SearchDatabase::class.java,
                        "search_image_database.db"
                    ).build()
                }
            }
            return database!!
        }

        fun destroyInstance() {
            database = null
        }
    }
}