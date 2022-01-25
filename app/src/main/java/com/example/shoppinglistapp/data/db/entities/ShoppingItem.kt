package com.example.shoppinglistapp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @ColumnInfo(name="name")
    var name: String,
    @ColumnInfo(name="amount")
    var amount: Int,
    @ColumnInfo(name="base_price")
    var base_price: String,
    @ColumnInfo(name="price")
    var price: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}