package com.geekbrains.glidepicassonlesson5.data

import androidx.room.*


@Dao
interface CatDAO {
    // Получить все из бд * - все столбцы
    @Query("SELECT * FROM cat_table")
    fun getAll(): List<Cat>

    // Вставка + обработка конфликта
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cats:List<Cat>)

    // Для получения данных по одному столбцу (может быть не entities может быть просто класс)
    @Query("SELECT cat_name FROM cat_table")
    fun getNames(): List<NamedCats>

    // Когда что-то надо найти в бд
    @Query("SELECT * FROM cat_table WHERE cat_name LIKE :search")
    fun searchCatByName(search:String) :List<Cat>

    // Конкретный диапазон котов
    @Query("SELECT * FROM cat_table WHERE cat_age BETWEEN :minAge AND :maxAge")
    fun getCatsAgeRange(minAge:Int, maxAge: Int):List<Cat>

    @Query("SELECT * FROM cat_table WHERE cat_age IN (:ages)")
    fun getSpecificAgeCats(ages: Array<Int>) :List<Cat>

    // Удалить 1 элемент через @DELETE, удалать все через @QUERY
}

data class NamedCats(
    @ColumnInfo (name = "cat_name")
    val name:String
)