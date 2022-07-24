package com.example.milatronicsdashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.MarketPrices
import com.example.milatronicsdashboard.databinding.FragmentPriceTrendsBinding

class PriceTrendsFragment : Fragment() {
    private lateinit var binding: FragmentPriceTrendsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPriceTrendsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = MarketPrices.builder()
            .location("Telangana")
            .count(90)
            .price(690.00)
            .build()

        Amplify.API.mutate(
            ModelMutation.create(item),
            { Log.i("API", "Added item : $it") },
            { Log.e("API", "Create failed", it) }
        )
    }
}