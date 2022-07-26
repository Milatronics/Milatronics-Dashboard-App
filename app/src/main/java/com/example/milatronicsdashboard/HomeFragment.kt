package com.example.milatronicsdashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.milatronicsdashboard.databinding.FragmentHomeBinding
import com.example.milatronicsdashboard.products.ProductItemAdapter
import com.example.milatronicsdashboard.products.ProductsDataSource
import com.example.milatronicsdashboard.shrimpdiseases.DiseaseDataSource
import com.example.milatronicsdashboard.shrimpdiseases.DiseaseItemAdapter
import com.example.milatronicsdashboard.shrimpfarmingtips.TipsDataSource
import com.example.milatronicsdashboard.shrimpfarmingtips.TipsItemAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val isHorizontal = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up products
        val productDataset = ProductsDataSource().loadProducts()
        binding.productsRecyclerView.adapter = ProductItemAdapter(activity as Context, productDataset, isHorizontal)
        binding.viewAllProducts.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_allProductsFragment)
        }

        // Set up shrimp diseases
        val diseaseDataset = DiseaseDataSource().loadProducts()
        binding.diseasesRecyclerView.adapter = DiseaseItemAdapter(activity as Context, diseaseDataset, isHorizontal)
        binding.viewAllDiseases.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_allDiseasesFragment)
        }

        // Set up shrimp farming tips
        val tipsDataset = TipsDataSource().loadProducts()
        binding.tipsRecyclerView.adapter = TipsItemAdapter(activity as Context, tipsDataset, isHorizontal)
        binding.viewAllTips.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_allTipsFragment)
        }
    }
}