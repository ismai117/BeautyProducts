package com.reachplc.interview.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "product_table")
data class Model(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val image: String,
    val description: String,
    val price: Double
) : Serializable