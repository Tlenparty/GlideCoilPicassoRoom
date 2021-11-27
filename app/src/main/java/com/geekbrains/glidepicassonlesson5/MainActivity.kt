package com.geekbrains.glidepicassonlesson5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.geekbrains.glidepicassonlesson5.data.Cat
import com.geekbrains.glidepicassonlesson5.data.MyDatabase
import com.geekbrains.glidepicassonlesson5.databinding.ActivityMainBinding
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    val catUrl = R.drawable.image
    val gifUrl =
        "https://upload.wikimedia.org/wikipedia/ru/archive/6/6b/20210505175821%21NyanCat.gif"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val MIGRATION_1_2 = object: Migration (1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                // Доабвили колонку с текстом без null заполнение по дефолту
                database.execSQL("ALTER TABLE cat_table ADD COLUMN favorite_food text NOT NULL DEFAULT ''")
            }

        }

        /**Room*/
        // Создаем бд  cat_db (название бд)
        val db = Room.databaseBuilder(applicationContext, MyDatabase::class.java, "cat_db")
          //  .addMigrations(MIGRATION_1_2)
           // .fallbackToDestructiveMigration() если не получилось сделать миграцию (редкий кейс)
            .build()
        val catDao = db.catDao()

        val cats = listOf(
            Cat(catName = "Barsik", catAge = 1),
            Cat(catName = "Vasili", catAge = 2),
            Cat(catName = "Morty", catAge = 3)
        )

        // Вход в бд не из главного потока!! Rx  или Coroutines
        Executors.newSingleThreadExecutor().execute {
            catDao.insertAll(cats)
            // val cats = catDao.getAll()
            val cats = catDao.getSpecificAgeCats(arrayOf(1, 3))
            println("VVV $cats")
        }


        /**Glide*/
        /*     Glide.with(this)
                 .load(catUrl) // Cюда можно ссылку на gif передать и будет все ок
                 .transition(DrawableTransitionOptions.withCrossFade(1500)) // плавное появление
                 .transform(RoundedCorners(50)) // скругленные углы
                 .transform(CircleCrop()) // круг
                 .transform(MultiTransformation(CenterCrop(), RoundedCorners(50))) // центр кроп + углы скругленные (для комбо)

                 .into(binding.image)*/


        /**Coil*/
/*
        val request = ImageRequest.Builder(this)
            .data(gifUrl)
            .target(binding.image)
            .build()

        val imageLoader = ImageLoader.Builder(this)
            .componentRegistry{
                if(SDK_INT >= 28){
                    add(ImageDecoderDecoder(this@MainActivity))
                } else{
                    add(GifDecoder())
                }
            }
            .build()

        imageLoader.enqueue(request)*/

        /*
        binding.image.load(catUrl){
           crossfade(1000) // медленное появление
            placeholder(R.drawable.ic_launcher_foreground)
            transformations(CircleCropTransformation()) // в кружочек
            transformations(RoundedCornersTransformation(150f)) // закруление краев
            transformations(GrayscaleTransformation()) // тлен

        } */

        /**Picasso*/
/*
        Picasso.get() // Доступ к классу
            .load(catUrl)
            .resize(250, 250) // изменить качество
            .centerCrop() // убирает черные рамки по кругу
            .transform(object : Transformation {
                override fun transform(source: Bitmap?): Bitmap {
                    TODO("Not yet implemented")
                }

                override fun key(): String = "key"

            }
            )
            .into(binding.image)*/
    }
}