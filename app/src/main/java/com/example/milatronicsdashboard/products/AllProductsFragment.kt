package com.example.milatronicsdashboard.products

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.milatronicsdashboard.databinding.FragmentAllProductsBinding

class AllProductsFragment : Fragment() {
    private lateinit var binding: FragmentAllProductsBinding
    lateinit var productDataset: List<Product>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAllProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productDataset = ProductsDataSource().loadProducts()
        binding.ourProductsRecyclerView.adapter = ProductItemAdapter(activity as Context, productDataset)
    }
}