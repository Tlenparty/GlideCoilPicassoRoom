package com.geekbrains.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// Сущность (таблица)
@Entity(tableName = "cat_table") // Название таблицы

data class Cat(
    // Автоматическая генерация id + первичный ключ
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "cat_name") val catName: String,
    @ColumnInfo(name = "cat_age") val catAge: Int
)
