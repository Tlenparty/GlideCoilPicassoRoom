package com.geekbrains.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CatDAO {
    // Получить все из бд * - все столбцы
    @Query("SELECT * FROM cat_table")
    fun getAll(): List<Cat>

    // Вставка + обработка конфликта
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cats:List<Cat>)
}