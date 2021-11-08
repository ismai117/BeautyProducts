package com.reachplc.interview.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reachplc.interview.model.Model

@Database(
    entities = [Model::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object {
        val TABLE_NAME = "product_table"
    }
}