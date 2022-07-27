package com.example.milatronicsdashboard

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.milatronicsdashboard.databinding.FragmentHomeBinding
import com.example.milatronicsdashboard.products.ProductItemAdapter
import com.example.milatronicsdashboard.products.ProductsDataSource
import com.example.milatronicsdashboard.shrimpdiseases.DiseaseDataSource
import com.example.milatronicsdashboard.shrimpdiseases.DiseaseItemAdapter
import com.example.milatronicsdashboard.shrimpfarmingtips.TipsDataSource
import com.example.milatronicsdashboard.shrimpfarmingtips.TipsItemAdapter
import kotlin.math.abs

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val isHorizontal = true
    
    //ADS stuff
    private lateinit var  viewPager2: ViewPager2
    private lateinit var handler : Handler
    private lateinit var imageList:ArrayList<Int>
    private lateinit var adapter: ImageAdapter
    

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        //some stuff for ADS

        viewPager2 = view.findViewById(R.id.viewPager2)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.ad1)
        imageList.add(R.drawable.ad2)
        imageList.add(R.drawable.ad3)
        imageList.add(R.drawable.ad4)


        adapter = ImageAdapter(imageList, viewPager2)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable , 2000)
            }
        })

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

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }


    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable , 2000)
    }
}