package com.example.milatronicsdashboard.shrimpdiseases

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
import com.example.milatronicsdashboard.products.ProductItemAdapter

/** Adapter for the recycler views in home and all items fragments. Displays [Disease] data object */
class DiseaseItemAdapter(
    private val context: Context,
    private val diseaseDataset: List<Disease>,
    private val isHorizontal: Boolean = false)
    : RecyclerView.Adapter<DiseaseItemAdapter.DiseaseItemViewHolder>()
{
    companion object{
        const val WIDTH = 230
        const val HEIGHT = 230
    }
    // Provide a reference to the views for each data item in the viewGroup
    class DiseaseItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val imageView: ImageView = view.findViewById(R.id.disease_item_image)
        val titleTextView: TextView = view.findViewById(R.id.disease_item_title)
    }

    /** Create new views (invoked by the layout manager) */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.disease_item, parent, false)
        return DiseaseItemViewHolder(adapterLayout)
    }

    /** Replace the contents of a view (invoked by the layout manager) */
    override fun onBindViewHolder(holder: DiseaseItemViewHolder, position: Int) {
        val disease = diseaseDataset[position]
        holder.titleTextView.text = context.resources.getString(disease.titleResourceId)
        holder.imageView.setImageResource(disease.imageResourceId)
        holder.view.setOnClickListener{
            val action = HomeFragmentDirections.actionToDiseaseFragment(disease = disease)
            findNavController(holder.view).navigate(action)
        }

        if(isHorizontal){
            holder.view.layoutParams.width = ProductItemAdapter.WIDTH
            holder.imageView.layoutParams.height = ProductItemAdapter.HEIGHT
        }
    }

    override fun getItemCount() = diseaseDataset.size
}