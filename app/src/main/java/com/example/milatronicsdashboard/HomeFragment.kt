package com.example.milatronicsdashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.milatronicsdashboard.databinding.FragmentHomeBinding
import com.example.milatronicsdashboard.products.Product
import com.example.milatronicsdashboard.products.ProductItemAdapter
import com.example.milatronicsdashboard.products.ProductsDataSource

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var productDataset: List<Product>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        productDataset = ProductsDataSource().loadProducts()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productsRecyclerView.adapter = ProductItemAdapter(activity as Context, productDataset)
        binding.productsRecyclerView.setHasFixedSize(true)
    }
}