package com.surli.surli_shinta_mvvm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.surli.surli_shinta_mvvm.data.model.Dog

@Database(entities = [Dog::class], version = 1)
abstract class  SearchDatabase : RoomDatabase() {

    abstract fun dog(): SearchImageDao

    companion object {
        private var database: SearchDatabase? = null

        fun instance(context: Context): SearchDatabase {
            if (database == null) {
                synchronized(SearchDatabase ::class) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        SearchDatabase ::class.java,
                        "search_database.db"
                    ).build()
                }
            }
            return database!!
        }
    }
}