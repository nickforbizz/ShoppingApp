package com.example.shoppinglistapp.data.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.shoppinglistapp.data.db.entities.ShoppingItem


@Database(
    entities = [ShoppingItem::class],
    version = 2
)
abstract class ShoppingDB: RoomDatabase() {

    // get your DAOs
    abstract  fun getShoppingDao(): ShoppingDao



    companion object {

        private var instance: ShoppingDB? = null
        private val LOCK = Any()

        val migration_1_2: Migration = object: Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE shopping_items add column base_price TEXT default ''")
            }
        }

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDB(context).also { instance = it }
        }

        fun createDB(context: Context) = Room.databaseBuilder(context.applicationContext,
        ShoppingDB::class.java, "shoppingDB.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}