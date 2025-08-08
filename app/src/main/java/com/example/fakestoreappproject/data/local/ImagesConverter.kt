package com.example.fakestoreappproject.data.local

import androidx.room.TypeConverter

class ImagesConverter {
    @TypeConverter
    fun fromImagesList(images: List<String>): String {
        return images.joinToString(separator = ",")
    }

    @TypeConverter
    fun toImagesList(images: String): List<String> {
        return images.split(",")
    }
}