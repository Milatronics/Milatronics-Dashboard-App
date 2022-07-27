package com.example.milatronicsdashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.amplifyframework.api.graphql.PaginatedResult
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.MarketPrice
import com.example.milatronicsdashboard.databinding.FragmentPriceTrendsBinding

class PriceTrendsFragment : Fragment() {
    private lateinit var binding: FragmentPriceTrendsBinding

    companion object {
        val LOCATIONS = listOf("Telangana", "Andhra Pradesh", "Vijayawada")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPriceTrendsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        // Set up the locations drop down menu
        val adapter = ArrayAdapter(requireContext(), R.layout.location_menu_item, LOCATIONS)
        binding.locationsDropdown.setAdapter(adapter)
        binding.locationsDropdown.setText(LOCATIONS[0], false)
        binding.locationsDropdown.setOnItemClickListener { _, _, _, _ ->
            fetchPrices(binding.locationsDropdown.text.toString())
        }
        fetchPrices(LOCATIONS[0])
    }

    private fun fetchPrices(location: String){
        clearTable()
        displayNoData()

        Amplify.API.query(
            ModelQuery.list(MarketPrice::class.java, MarketPrice.LOCATION.eq(location)),
            {displayData(it.data)},
            {Log.e("API", "Query failed", it)}
        )
    }

    private fun displayData(data: PaginatedResult<MarketPrice>){
        activity?.runOnUiThread{
            if(!data.items.none())
                clearTable()

            data.sortedByDescending {it.count}.forEach{ marketPrice ->
                Log.i("API", "$marketPrice")

                val count = TextView(requireContext())
                count.text = marketPrice.count.toString()
                count.textAlignment = TEXT_ALIGNMENT_CENTER

                val price = TextView(requireContext())
                price.text = String.format("\u20B9 %.2f", marketPrice.price)
                price.textAlignment = TEXT_ALIGNMENT_CENTER

                val newRow = TableRow(requireContext())
                newRow.setPadding(0, 8, 0, 8)
                newRow.addView(count)
                newRow.addView(price)

                binding.pricesTableLayout.addView(newRow)
            }
        }
    }

    private fun displayNoData(){
        val noDataText = TextView(requireContext())
        noDataText.text = getString(R.string.no_data_found)
        noDataText.textAlignment = TEXT_ALIGNMENT_CENTER

        val noDataRow = TableRow(requireContext())
        noDataRow.setPadding(0, 8, 0, 8)
        noDataRow.addView(noDataText)

        val noDataTextLayoutParams = noDataText.layoutParams as TableRow.LayoutParams
        noDataTextLayoutParams.span = 2
        noDataText.layoutParams = noDataTextLayoutParams

        binding.pricesTableLayout.addView(noDataRow)
    }

    private fun clearTable(){
        binding.pricesTableLayout.removeViews(1, binding.pricesTableLayout.childCount - 1)
    }
}