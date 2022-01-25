package com.example.shoppinglistapp.other

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currShoppingItem = items[position]
        holder.itemView.tv_name.text = currShoppingItem.name
        holder.itemView.tv_amount.text = currShoppingItem.amount.toString()
        holder.itemView.tv_price.text = "${currShoppingItem.price}  /="


        holder.itemView.iv_delete.setOnClickListener{
            viewModel.delete(currShoppingItem)
        }

        holder.itemView.iv_plus.setOnClickListener{
            //val amount = currShoppingItem.amount++
            val amount = ++currShoppingItem.amount
            currShoppingItem.price = (currShoppingItem.base_price.toInt() * amount).toString()
            Log.d("check",currShoppingItem.price)
            viewModel.upsert(currShoppingItem)
        }

        holder.itemView.iv_minus.setOnClickListener{
            if (currShoppingItem.amount > 1){
                //val amount = currShoppingItem.amount--
                val amount = --currShoppingItem.amount
                currShoppingItem.price = (currShoppingItem.base_price.toInt() * amount).toString()
                Log.d("check",currShoppingItem.price)
                viewModel.upsert(currShoppingItem)
            }else{
                Toast.makeText(holder.itemView.getContext(), "Amount cannot be less than 1", Toast.LENGTH_LONG).show()
            }


        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}