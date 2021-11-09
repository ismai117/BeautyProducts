package com.reachplc.interview.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reachplc.interview.model.Model
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {


    @Query("SELECT * FROM product_table")
    fun getProducts(): Flow<List<Model>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: List<Model>)

    @Query("DELETE FROM product_table")
    fun deleteAll()

    @Query("SELECT * FROM product_table WHERE name LIKE :product")
    fun searchDatabase(product: String): Flow<List<Model>>


}