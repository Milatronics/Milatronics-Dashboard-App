package com.example.milatronicsdashboard.shrimpfarmingtips

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.milatronicsdashboard.databinding.FragmentAllTipsBinding

class AllTipsFragment : Fragment() {
    private lateinit var binding: FragmentAllTipsBinding
    lateinit var tipsDataset: List<Tip>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAllTipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tipsDataset = TipsDataSource().loadProducts()
        binding.shrimpFarmingTipsRecyclerView.adapter = TipsItemAdapter(activity as Context, tipsDataset)
    }
}