package com.reachplc.interview.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reachplc.interview.R
import com.reachplc.interview.model.Model
import kotlinx.android.synthetic.main.product_item_layout.view.*
import kotlin.math.roundToInt

class ListAdapter : RecyclerView.Adapter<ListAdapter.ProductViewHolder>() {

    lateinit var products: List<Model>

    fun setProduct(products: List<Model>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ProductViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun bind(model: Model) {

            Glide.with(itemView).load(model.image).into(itemView.productImage)
            itemView.productName.text = model.name
            itemView.productPrice.text = "£${model.price.roundToInt()}"

            itemView.setOnClickListener {

                val action = ListFragmentDirections.actionFragmentListToDetailFragment()
                action.model  = model
                Navigation.findNavController(it).navigate(action)

            }

        }

    }

}