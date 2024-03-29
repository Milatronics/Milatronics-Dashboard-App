package com.example.milatronicsdashboard.shrimpdiseases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.milatronicsdashboard.databinding.FragmentDiseaseBinding

class DiseaseFragment : Fragment() {
    private lateinit var binding: FragmentDiseaseBinding
    private lateinit var disease: Disease

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDiseaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DiseaseFragmentArgs by navArgs()
        disease = args.disease

        (activity as AppCompatActivity).supportActionBar?.title = getString(disease.titleResourceId)
        binding.diseaseTitle.text = getString(disease.titleResourceId)
        binding.diseaseImage.setImageResource(disease.imageResourceId)
        binding.diseaseDescription.text = getText(disease.descriptionResourceId)
    }
}