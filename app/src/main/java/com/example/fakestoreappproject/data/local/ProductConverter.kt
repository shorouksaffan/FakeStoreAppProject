package com.example.fakestoreappproject.data.local

import androidx.room.TypeConverter
import com.example.fakestoreappproject.data.model.Product

class ProductConverter {
    private val categoryConverter = CategoryConverter()
    private val imagesConverter = ImagesConverter()

    @TypeConverter
    fun fromProduct(product: Product) : String {
        val category = categoryConverter.fromCategory(product.category)
        val images = imagesConverter.fromImagesList(product.images)

        return listOf(
            product.id,
            product.title,
            product.description,
            product.price,
            category,
            images,
            product.slug,
            product.creationAt,
            product.updatedAt
        ).joinToString(separator = ",")
    }

    @TypeConverter
    fun toProduct(product: String) : Product {
        val parts = product.split(",")
        val category = categoryConverter.toCategory(parts[4])
        val images = imagesConverter.toImagesList(parts[5])

        return Product(
            id = parts[0].toInt(),
            title = parts[1],
            description = parts[2],
            price = parts[3].toInt(),
            category = category,
            images = images,
            slug = parts[6],
            creationAt = parts[7],
            updatedAt = parts[8]
        )
    }
}