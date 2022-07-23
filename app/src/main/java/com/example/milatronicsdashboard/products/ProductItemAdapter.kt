package com.example.milatronicsdashboard.products

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.milatronicsdashboard.HomeFragmentDirections
import com.example.milatronicsdashboard.R

/** Adapter for the recycler view in home fragment. Displays [Product] data object */
class ProductItemAdapter(
    private val context: Context,
    private val productDataset: List<Product>)
    : RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder>()
{
    // Provide a reference to the views for each data item in the viewGroup
    class ProductItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val imageView: ImageView = view.findViewById(R.id.product_item_image)
        val titleTextView: TextView = view.findViewById(R.id.product_item_title)
    }

    /** Create new views (invoked by the layout manager) */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ProductItemViewHolder(adapterLayout)
    }

    /** Replace the contents of a view (invoked by the layout manager) */
    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val product = productDataset[position]
        holder.titleTextView.text = context.resources.getString(product.titleResourceId)
        holder.imageView.setImageResource(product.imageResourceId)

        holder.view.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToProductFragment(product = product)
            findNavController(holder.view).navigate(action)
        }
    }

    override fun getItemCount() = productDataset.size
}