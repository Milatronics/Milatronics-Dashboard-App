package com.example.milatronicsdashboard.shrimpfarmingtips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.milatronicsdashboard.databinding.FragmentTipBinding

class TipFragment : Fragment() {
    private lateinit var binding: FragmentTipBinding
    private lateinit var tip: Tip

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTipBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: TipFragmentArgs by navArgs()
        tip = args.tip

        (activity as AppCompatActivity).supportActionBar?.title = getString(tip.titleResourceId)
        binding.tipTitle.text = context?.resources?.getString(tip.titleResourceId)
        binding.tipImage.setImageResource(tip.imageResourceId)
        binding.tipDescription.text = context?.resources?.getString(tip.descriptionResourceId)
    }
}