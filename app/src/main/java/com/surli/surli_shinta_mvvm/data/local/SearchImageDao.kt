package com.surli.surli_shinta_mvvm.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.surli.surli_shinta_mvvm.data.model.Dog

@Dao
interface SearchImageDao {

    @Query("select * from dogs")
    fun getAllDogs(): List<Dog>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDogs(dogs: List<Dog>)
}

