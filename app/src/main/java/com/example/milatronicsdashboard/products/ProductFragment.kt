package com.example.milatronicsdashboard.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.milatronicsdashboard.databinding.FragmentProductBinding

class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding
    private lateinit var product: Product

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ProductFragmentArgs by navArgs()
        product = args.product

        binding.productTitle.text = context?.resources?.getString(product.titleResourceId)
        binding.productImage.setImageResource(product.imageResourceId)
        binding.productDescription.text = context?.resources?.getString(product.descriptionResourceId)
    }
}