package com.geekbrains.glidepicassonlesson5.data

import androidx.room.Database
import androidx.room.RoomDatabase

// Указываем таблцы которые есть в бд
@Database(entities = [Cat::class], version = 1, exportSchema = true)
abstract class MyDatabase : RoomDatabase() {
    // метод предоставляет объект DАО
    abstract fun catDao(): CatDAO
}