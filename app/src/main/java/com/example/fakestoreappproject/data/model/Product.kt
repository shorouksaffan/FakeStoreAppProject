package com.example.fakestoreappproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    val category: Category,
    val creationAt: String,
    val description: String,
    @PrimaryKey
    val id: Int,
    val images: List<String>,
    val price: Int,
    val slug: String,
    val title: String,
    val updatedAt: String
)