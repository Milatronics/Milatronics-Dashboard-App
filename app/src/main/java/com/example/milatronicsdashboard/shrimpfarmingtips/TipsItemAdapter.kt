package com.example.milatronicsdashboard.shrimpfarmingtips

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
import com.example.milatronicsdashboard.shrimpdiseases.Disease

/** Adapter for the recycler views in home and all items fragments. Displays [Disease] data object */
class TipsItemAdapter(
    private val context: Context,
    private val tipDataset: List<Tip>,
    private val itemWidth: Int = 0)
    : RecyclerView.Adapter<TipsItemAdapter.TipItemViewHolder>()
{
    // Provide a reference to the views for each data item in the viewGroup
    class TipItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val imageView: ImageView = view.findViewById(R.id.tip_item_image)
        val titleTextView: TextView = view.findViewById(R.id.tip_item_title)
    }

    /** Create new views (invoked by the layout manager) */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.tip_item, parent, false)
        return TipItemViewHolder(adapterLayout)
    }

    /** Replace the contents of a view (invoked by the layout manager) */
    override fun onBindViewHolder(holder: TipItemViewHolder, position: Int) {
        val tip = tipDataset[position]
        holder.titleTextView.text = context.resources.getString(tip.titleResourceId)
        holder.imageView.setImageResource(tip.imageResourceId)
        holder.view.setOnClickListener{
            val action = HomeFragmentDirections.actionToTipFragment(tip = tip)
            findNavController(holder.view).navigate(action)
        }
        if(itemWidth != 0)
            holder.view.layoutParams.width = itemWidth
    }

    override fun getItemCount() = tipDataset.size
}