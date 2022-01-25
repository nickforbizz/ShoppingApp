package com.example.shoppinglistapp.ui.shoppinglist

import com.example.shoppinglistapp.data.db.entities.ShoppingItem

interface AddDialogueListener {
    fun onAddShoppingItem(item: ShoppingItem)
}