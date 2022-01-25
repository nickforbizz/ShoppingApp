package com.example.shoppinglistapp.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialogue_add_shopping_item.*

class AddShoppingItemDialog(context: Context, var addDialogueListener: AddDialogueListener): AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialogue_add_shopping_item)

        tv_add.setOnClickListener{
            val name = et_name.text.toString()
            val price = et_price.text.toString()
            val base_price = price
            val amount = 1

            if (name.isEmpty() || price.isEmpty()){
                Toast.makeText(context, "please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount, base_price, price)
            addDialogueListener.onAddShoppingItem(item)
            dismiss()
        }

        tv_cancel.setOnClickListener{
            cancel()
        }
    }
}