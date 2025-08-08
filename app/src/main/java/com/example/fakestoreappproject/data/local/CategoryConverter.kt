package com.example.fakestoreappproject.data.local

import androidx.room.TypeConverter
import com.example.fakestoreappproject.data.model.Category

class CategoryConverter {
    @TypeConverter
    fun fromCategory(category: Category): String {
        return listOf(
            category.name,
            category.creationAt,
            category.id.toString(),
            category.image,
            category.slug,
            category.updatedAt
        ).joinToString(separator = ",")
    }

    @TypeConverter
    fun toCategory(category: String): Category {
        val parts = category.split(",")
        return Category(
            name = parts[0],
            creationAt = parts[1],
            id = parts[2].toInt(),
            image = parts[3],
            slug = parts[4],
            updatedAt = parts[5]
        )
    }
}